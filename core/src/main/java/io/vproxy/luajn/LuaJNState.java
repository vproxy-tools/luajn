package io.vproxy.luajn;

import io.vproxy.luajn.n.Consts;
import io.vproxy.luajn.n.ConstsLua5_2;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.LuaState;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIString;

import java.lang.foreign.MemorySegment;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class LuaJNState implements AutoCloseable {
    private final LuaState L;
    private final LuaJNTable _G;
    private final LuaJNTable registry;

    private final LuaJNState parentL;
    private final MemorySegment registryName; // ensure the thread won't be gc-ed

    public LuaJNState(Allocator allocator) {
        this(Lua.get().newState(allocator));
    }

    public LuaJNState(LuaState L) {
        this(L, null, MemorySegment.NULL);
    }

    public LuaJNState(LuaState L, LuaJNState parentL, MemorySegment registryName) {
        this.L = L;
        if (parentL == null) {
            registryIndexCounter = new AtomicLong();
        } else {
            registryIndexCounter = parentL.getMainState().registryIndexCounter;
        }

        if (Lua.get().version(L) >= 502) {
            L.pushInteger(ConstsLua5_2.LUA_RIDX_GLOBALS);
            L.getTable(Consts.LUA_REGISTRYINDEX);
            var tmpVar = storeTemporaryVariable();
            _G = new LuaJNTable(this, -1, tmpVar);
        } else {
            _G = new LuaJNTable(this, Consts.LUA_GLOBALSINDEX, MemorySegment.NULL);
        }
        registry = new LuaJNTable(this, Consts.LUA_REGISTRYINDEX, MemorySegment.NULL);
        this.parentL = parentL == null ? null : parentL.getMainState();
        this.registryName = registryName;
    }

    @Override
    public void close() {
        if (Lua.get().version(L) >= 502) {
            _G.close();
        }
        if (registryName.address() != 0) {
            removeTemporaryVariable(registryName);
        }
        if (parentL == null) {
            L.close();
        }
    }

    public boolean isCoroutine() {
        return parentL != null;
    }

    public int getStatus() {
        return Lua.get().status(L);
    }

    public LuaJNState getMainState() {
        var ret = this;
        while (ret.parentL != null) {
            ret = ret.parentL;
        }
        return ret;
    }

    public void __push(LuaJNState L) {
        if (parentL == null) {
            throw new UnsupportedOperationException();
        }
        this.L.pushThread();
        Lua.get().xmove(this.L, L.getLuaState(), 1);

        if (!L.getLuaState().isThread(-1)) {
            var typename = Lua.get().typename(L.getLuaState(), L.getLuaState().type(-1)).toString();
            L.getLuaState().pop(1);
            throw new LuaCallException("stack top element is not thread: " + typename);
        }
    }

    public LuaState getLuaState() {
        return L;
    }

    public LuaJNTable getGlobal() {
        return _G;
    }

    public LuaJNTable getRegistry() {
        return registry;
    }

    /**
     * Similar to {@link LuaJNFunction#invoke(Consumer)}, only for resuming a coroutine
     */
    public LuaJNCallResult resume(Consumer<LuaJNState> argsStackOp) {
        checkStackTopIsFunctionForResuming();

        int stackSizeBeforeArgs = L.getTop();
        argsStackOp.accept(this);
        int stackSizeAfterArgs = L.getTop();
        int nargs = stackSizeAfterArgs - stackSizeBeforeArgs;
        if (nargs < 0) {
            throw new IllegalArgumentException("nargs = " + stackSizeAfterArgs + " - " + stackSizeBeforeArgs + " < 0");
        }
        int err = Lua.get().resume(L, nargs);
        if (err == 0 || err == Consts.LUA_YIELD) {
            int nresults = L.getTop();
            return new LuaJNCallResult(nresults, this);
        }
        if (L.getTop() < 1) {
            throw new LuaCallException("calling function failed and no error message on stack");
        }
        if (!L.isString(-1)) {
            throw new LuaCallException("calling function failed and stack top element is not error message: " +
                                       Lua.get().typename(L, L.type(-1)));
        }
        var msg = L.toString(-1).toString();
        L.pop(1);
        throw new LuaCallException(msg);
    }

    /**
     * Similar to {@link LuaJNFunction#invoke(Object...)}, only for resuming a coroutine
     */
    public Object resume(Object... args) {
        return resume(Allocator.ofDummy(), args);
    }

    /**
     * Similar to {@link LuaJNFunction#invoke(Allocator, Object...)}, only for resuming a coroutine
     */
    public Object resume(Allocator allocator, Object... args) {
        checkStackTopIsFunctionForResuming();

        var res = resume(ctxLL -> {
            try (var allocator0 = Allocator.ofPooled()) {
                for (var arg : args) {
                    LuaJNative.pushStack(arg, ctxLL, allocator0);
                }
            }
        });

        if (res.nresults == 0) {
            return res.result();
        }
        for (int i = 0; i < res.nresults; ++i) {
            res.convert(LL -> LuaJNative.formatResult(this, allocator));
        }
        return res.result();
    }

    private void checkStackTopIsFunctionForResuming() {
        if (!isCoroutine()) {
            throw new UnsupportedOperationException("this LuaState is not a coroutine");
        }
        if (getStatus() == Consts.LUA_YIELD) {
            if (L.getTop() != 0) {
                throw new IllegalStateException("stack should be empty when try to resume when it's in LUA_YIELD mode");
            }
            return;
        }
        int n = L.getTop();
        if (n == 0) {
            throw new IllegalStateException("cannot resume an empty LuaState");
        }
        if (!L.isFunction(-1)) {
            throw new IllegalStateException("the LuaState doesn't have function on stack top");
        }
        if (n != 1) {
            throw new IllegalStateException("stack should only contain the function to resume for starting the coroutine");
        }
    }

    private final AtomicLong registryIndexCounter;

    private MemorySegment __generateNextTmpVarIndex() {
        var n = registryIndexCounter.incrementAndGet();
        while (n <= 0) {
            registryIndexCounter.set(0);
            n = registryIndexCounter.incrementAndGet();
        }
        return MemorySegment.ofAddress(n);
    }

    public MemorySegment storeTemporaryVariable() {
        assert L.getTop() >= 1; // the object to store should be on stack

        L.pushLightUserData(Consts.TEMPORARY_VARIABLES_TABLE_INDEX);
        L.getTable(Consts.LUA_REGISTRYINDEX);
        if (!L.isTable(-1)) {
            L.pop(1);
            L.pushLightUserData(Consts.TEMPORARY_VARIABLES_TABLE_INDEX);
            L.newTable();
            L.setTable(Consts.LUA_REGISTRYINDEX);
            L.pushLightUserData(Consts.TEMPORARY_VARIABLES_TABLE_INDEX);
            L.getTable(Consts.LUA_REGISTRYINDEX);
        }
        L.insert(-2);
        var index = __generateNextTmpVarIndex();
        L.pushLightUserData(index);
        L.insert(-2);
        L.setTable(-3);
        L.pop(1);
        return index;
    }

    public void removeTemporaryVariable(MemorySegment index) {
        L.pushLightUserData(Consts.TEMPORARY_VARIABLES_TABLE_INDEX);
        L.getTable(Consts.LUA_REGISTRYINDEX);
        if (!L.isTable(-1)) {
            L.pop(1);
            System.out.println("[luajn] cannot find temporary variables table but removeTemporaryVariable(" + index + ") is called");
            return;
        }
        L.pushLightUserData(index);
        L.pushNil();
        L.setTable(-3);
        L.pop(1);
    }

    public void pushTemporaryVariable(MemorySegment index) {
        L.pushLightUserData(Consts.TEMPORARY_VARIABLES_TABLE_INDEX);
        L.getTable(Consts.LUA_REGISTRYINDEX);
        if (!L.isTable(-1)) {
            L.pop(1);
            L.pushNil();
            System.out.println("[luajn] cannot find temporary variables table but pushTemporaryVariable(" + index + ") is called");
            return;
        }
        L.pushLightUserData(index);
        L.getTable(-2);
        L.insert(-2);
        L.pop(1);
    }

    private void load0(String lua, int nresults) {
        try (var allocator = Allocator.ofPooled()) {
            Lua.get().loadString(L, new PNIString(allocator, lua));
        }
        int err = L.pcall(0, nresults, 0);
        if (err == 0) {
            return;
        }
        if (L.getTop() < 1) {
            throw new LuaCallException("executing lua failed and no error message on stack");
        }
        if (!L.isString(-1)) {
            throw new LuaCallException("executing lua failed and stack top element is not error message: " +
                                       Lua.get().typename(L, L.type(-1)));
        }
        var msg = L.toString(-1).toString();
        L.pop(1);
        throw new LuaCallException(msg);
    }

    public void load(String lua) {
        load0(lua, 0);
    }

    public long loadLong(String lua) {
        load0(lua, 1);
        if (L.getTop() < 1) {
            throw new LuaCallException("no number on stack");
        }
        if (!L.isNumber(-1)) {
            var typename = Lua.get().typename(L, L.type(-1)).toString();
            L.pop(1);
            throw new LuaCallException("stack top element is not number: " + typename);
        }
        long n = L.toInteger(-1);
        L.pop(1);
        return n;
    }

    public double loadNumber(String lua) {
        load0(lua, 1);
        if (L.getTop() < 1) {
            throw new LuaCallException("no number on stack");
        }
        if (!L.isNumber(-1)) {
            var typename = Lua.get().typename(L, L.type(-1)).toString();
            L.pop(1);
            throw new LuaCallException("stack top element is not number: " + typename);
        }
        double n = L.toNumber(-1);
        L.pop(1);
        return n;
    }

    public String loadString(String lua) {
        load0(lua, 1);
        if (L.getTop() < 1) {
            throw new LuaCallException("no string on stack");
        }
        if (!L.isString(-1)) {
            var typename = Lua.get().typename(L, L.type(-1)).toString();
            L.pop(1);
            throw new LuaCallException("stack top element is not string: " + typename);
        }
        String s = L.toString(-1).toString();
        L.pop(1);
        return s;
    }

    public boolean loadBoolean(String lua) {
        load0(lua, 1);
        if (L.getTop() < 1) {
            throw new LuaCallException("no boolean on stack");
        }
        if (!L.isBoolean(-1)) {
            var typename = Lua.get().typename(L, L.type(-1)).toString();
            L.pop(1);
            throw new LuaCallException("stack top element is not boolean: " + typename);
        }
        boolean b = L.toBoolean(-1);
        L.pop(1);
        return b;
    }

    public LuaJNTable loadTable(String lua) {
        load0(lua, 1);
        if (L.getTop() < 1) {
            throw new LuaCallException("no table on stack");
        }
        if (!L.isTable(-1)) {
            var typename = Lua.get().typename(L, L.type(-1)).toString();
            L.pop(1);
            throw new LuaCallException("stack top element is not table: " + typename);
        }
        var name = storeTemporaryVariable();
        return new LuaJNTable(this, -1, name);
    }

    public LuaJNFunction loadFunction(String lua) {
        load0(lua, 1);
        if (L.getTop() < 1) {
            throw new LuaCallException("no function on stack");
        }
        if (!L.isFunction(-1)) {
            var typename = Lua.get().typename(L, L.type(-1)).toString();
            L.pop(1);
            throw new LuaCallException("stack top element is not function: " + typename);
        }
        var name = storeTemporaryVariable();
        return new LuaJNFunction(this, name);
    }

    public LuaJNState loadCoroutine(Allocator allocator, String lua) {
        load0(lua, 1);
        if (L.getTop() < 1) {
            throw new LuaCallException("no coroutine on stack");
        }
        if (!L.isThread(-1)) {
            var typename = Lua.get().typename(L, L.type(-1)).toString();
            L.pop(1);
            throw new LuaCallException("stack top element is not coroutine: " + typename);
        }
        var T = L.toThread(-1, allocator);
        var name = storeTemporaryVariable();
        return new LuaJNState(T, this, name);
    }

    public LuaJNTable newTable() {
        L.newTable();
        var name = storeTemporaryVariable();
        return new LuaJNTable(this, -1, name);
    }

    public LuaJNUserData newUserData(long size) {
        var mem = L.newUserData(size);
        var name = storeTemporaryVariable();
        return new LuaJNUserData(this, name, mem.reinterpret(size));
    }

    public LuaJNState newCoroutine(Allocator allocator) {
        var root = getMainState();
        var luaState = Lua.get().newThread(root.getLuaState(), allocator);
        var name = storeTemporaryVariable();
        return new LuaJNState(luaState, root, name);
    }
}
