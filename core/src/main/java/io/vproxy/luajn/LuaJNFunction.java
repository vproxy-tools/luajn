package io.vproxy.luajn;

import io.vproxy.luajn.n.Consts;
import io.vproxy.luajn.n.Lua;
import io.vproxy.pni.Allocator;

import java.lang.foreign.MemorySegment;
import java.util.function.Consumer;

public class LuaJNFunction implements AutoCloseable {
    private final LuaJNState L;
    private final MemorySegment registryName;

    LuaJNFunction(LuaJNState L, MemorySegment registryName) {
        this.L = L;
        this.registryName = registryName;
    }

    @Override
    public void close() {
        L.removeTemporaryVariable(registryName);
    }

    public void __push(LuaJNState L) {
        L.pushTemporaryVariable(registryName);
        if (!L.getLuaState().isFunction(-1)) {
            var typename = Lua.get().typename(L.getLuaState(), L.getLuaState().type(-1)).toString();
            L.getLuaState().pop(1);
            throw new LuaCallException("stack top element is not function: " + typename);
        }
    }

    /**
     * Same as {@link #invoke(LuaJNState, Consumer)} with the LuaJNState where the function is retrieved from.
     *
     * @param argsStackOp push values into the stack as function arguments
     * @return number of returned values
     */
    public LuaJNCallResult invoke(Consumer<LuaJNState> argsStackOp) {
        return invoke(L, argsStackOp);
    }

    /**
     * @param argsStackOp push values into the stack as function arguments
     * @return number of returned values
     */
    public LuaJNCallResult invoke(LuaJNState L, Consumer<LuaJNState> argsStackOp) {
        if (L.isCoroutine()) {
            if (L.getStatus() == Consts.LUA_YIELD) {
                throw new IllegalStateException("Cannot call LuaJNFunction#invoke on a LUA_YIELD coroutine. You should call LuaJNState#resume");
            }
            if (L.getLuaState().getTop() != 0) {
                throw new IllegalStateException("Cannot call LuaJNFunction#invoke on coroutine with non-empty stack");
            }
        }

        __push(L);
        int stackSizeBeforeArgs = L.getLuaState().getTop();
        argsStackOp.accept(L);
        int stackSizeAfterArgs = L.getLuaState().getTop();
        int nargs = stackSizeAfterArgs - stackSizeBeforeArgs;
        if (nargs < 0) {
            throw new IllegalArgumentException("nargs = " + stackSizeAfterArgs + " - " + stackSizeBeforeArgs + " < 0");
        }
        int err;
        if (L.isCoroutine()) {
            err = Lua.get().resume(L.getLuaState(), nargs);
        } else {
            err = L.getLuaState().pcall(nargs, Consts.LUA_MULTRET, 0);
        }
        if (err == 0 || (L.isCoroutine()) && err == Consts.LUA_YIELD) {
            int stackSizeAfterCall = L.getLuaState().getTop();
            int nresults = stackSizeAfterCall - stackSizeBeforeArgs + 1;
            return new LuaJNCallResult(nresults, L);
        }
        if (L.getLuaState().getTop() < 1) {
            throw new LuaCallException("calling function failed and no error message on stack");
        }
        if (!L.getLuaState().isString(-1)) {
            throw new LuaCallException("calling function failed and stack top element is not error message: " +
                                       Lua.get().typename(L.getLuaState(), L.getLuaState().type(-1)));
        }
        var msg = L.getLuaState().toString(-1).toString();
        L.getLuaState().pop(1);
        throw new LuaCallException(msg);
    }

    /**
     * Same as {@link #invoke(LuaJNState, Object...)} with the LuaJNState where the function is retrieved from.
     *
     * @see #invoke(LuaJNState, Consumer)
     */
    public Object invoke(Object... args) {
        return invoke(L, args);
    }

    /**
     * Same as {@link #invoke(LuaJNState, Allocator, Object...)} with a dummy allocator.
     *
     * @see #invoke(LuaJNState, Consumer)
     */
    public Object invoke(LuaJNState L, Object... args) {
        return invoke(L, Allocator.ofDummy(), args);
    }

    /**
     * Same as {@link #invoke(LuaJNState, Allocator, Object...)} with the LuaJNState where the function is retrieved from.
     *
     * @see #invoke(LuaJNState, Consumer)
     */
    public Object invoke(Allocator allocator, Object... args) {
        return invoke(L, allocator, args);
    }

    /**
     * @param L         the LuaState to run this function on
     * @param allocator the allocator for building result (if you are sure it won't be used, it can be a dummy allocator)
     * @param args      arguments for calling the lua function
     * @return return value(s) of the lua function.
     * <ul>
     * <li>If <code>nresults</code> is 0, the return value will be <code>null</code>.</li>
     * <li>If <code>nresults</code> is 1, the return value will be the converted lua object</li>
     * <li>If <code>nresults</code> is greater than 1, the return value will be an array containing all the converted lua objects</li>
     * </ul>
     */
    public Object invoke(LuaJNState L, Allocator allocator, Object... args) {
        var res = invoke(L, ctxLL -> {
            try (var allocator1 = Allocator.ofPooled()) {
                for (var arg : args) {
                    LuaJNative.pushStack(arg, ctxLL, allocator1);
                }
            }
        });

        if (res.nresults == 0) {
            return res.result();
        }
        for (int i = 0; i < res.nresults; ++i) {
            res.convert(LL -> LuaJNative.formatResult(L, allocator));
        }
        return res.result();
    }
}
