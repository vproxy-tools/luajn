package io.vproxy.luajn;

import io.vproxy.luajn.n.Consts;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.LuaState;
import io.vproxy.pni.*;

import java.lang.foreign.MemorySegment;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public class LuaJNTable implements AutoCloseable {
    private final LuaJNState L;
    private final int tableIndex;
    private final MemorySegment registryName;

    LuaJNTable(LuaJNState L, int tableIndex, MemorySegment registryName) {
        this.L = L;
        this.tableIndex = tableIndex;
        this.registryName = registryName;
    }

    @Override
    public void close() {
        if (registryName.address() == 0) {
            return; // do nothing
        }
        L.removeTemporaryVariable(registryName);
    }

    public void __push(LuaJNState L) {
        if (registryName.address() == 0) {
            throw new UnsupportedOperationException();
        }
        L.pushTemporaryVariable(registryName);
        if (!L.getLuaState().isTable(-1)) {
            var typename = Lua.get().typename(L.getLuaState(), L.getLuaState().type(-1)).toString();
            L.getLuaState().pop(1);
            throw new LuaCallException("stack top element is not table: " + typename);
        }
    }

    private void preOp() {
        if (registryName.address() == 0) {
            return;
        }
        __push(L);
    }

    private void postOp() {
        if (registryName.address() == 0) {
            return;
        }
        L.getLuaState().pop(1);
    }

    private int getTableIndexForGetField() {
        if (registryName.address() == 0) {
            return tableIndex;
        }
        return tableIndex;
    }

    private int getTableIndexForSetField() {
        if (registryName.address() == 0) {
            return tableIndex;
        }
        return tableIndex - 1;
    }

    private int getTableIndexForGetTable() {
        if (registryName.address() == 0) {
            return tableIndex;
        }
        return tableIndex - 1;
    }

    private int getTableIndexForSetTable() {
        if (registryName.address() == 0) {
            return tableIndex;
        }
        return tableIndex - 2;
    }

    private void set(String name) {
        try (var allocator = Allocator.ofPooled()) {
            L.getLuaState().setField(getTableIndexForSetField(), new PNIString(allocator, name));
        }
    }

    private int getType(String name) {
        try (var allocator = Allocator.ofPooled()) {
            L.getLuaState().getField(getTableIndexForGetField(), new PNIString(allocator, name));
        }
        int type = L.getLuaState().type(-1);
        L.getLuaState().pop(1);
        return type;
    }

    private int getType(int index) {
        L.getLuaState().pushInteger(index);
        L.getLuaState().getTable(getTableIndexForGetTable());
        int type = L.getLuaState().type(-1);
        L.getLuaState().pop(1);
        return type;
    }

    private void get(String name, int expectedType) {
        try (var allocator = Allocator.ofPooled()) {
            L.getLuaState().getField(getTableIndexForGetField(), new PNIString(allocator, name));
        }
        checkTypeOnStack(expectedType);
    }

    private void get(int index, int expectedType) {
        L.getLuaState().pushInteger(index);
        L.getLuaState().getTable(getTableIndexForGetTable());
        checkTypeOnStack(expectedType);
    }

    private void checkTypeOnStack(int expectedType) {
        if (expectedType == -1) {
            return;
        }
        int type = L.getLuaState().type(-1);
        if (type != expectedType) {
            L.getLuaState().pop(1);
            postOp();
            throw new ClassCastException(
                Lua.get().typename(L.getLuaState(), type) +
                " cannot be cast to " + Lua.get().typename(L.getLuaState(), expectedType));
        }
    }

    public int arrayLength() {
        preOp();
        int len = (int) L.getLuaState().objLen(tableIndex);
        postOp();
        return len;
    }

    public void put(String name, long n) {
        preOp();
        L.getLuaState().pushInteger(n);
        set(name);
        postOp();
    }

    public void set(int index, long n) {
        preOp();
        L.getLuaState().pushInteger(index);
        L.getLuaState().pushInteger(n);
        L.getLuaState().setTable(getTableIndexForSetTable());
        postOp();
    }

    public long getLong(String name) {
        preOp();
        get(name, Consts.LUA_TNUMBER);
        long n = L.getLuaState().toInteger(-1);
        L.getLuaState().pop(1);
        postOp();
        return n;
    }

    public long getLong(int index) {
        preOp();
        get(index, Consts.LUA_TNUMBER);
        long n = L.getLuaState().toInteger(-1);
        L.getLuaState().pop(1);
        postOp();
        return n;
    }

    public void put(String name, double n) {
        preOp();
        L.getLuaState().pushNumber(n);
        set(name);
        postOp();
    }

    public void set(int index, double n) {
        preOp();
        L.getLuaState().pushInteger(index);
        L.getLuaState().pushNumber(n);
        L.getLuaState().setTable(getTableIndexForSetTable());
        postOp();
    }

    public double getNumber(String name) {
        preOp();
        get(name, Consts.LUA_TNUMBER);
        double n = L.getLuaState().toNumber(-1);
        L.getLuaState().pop(1);
        postOp();
        return n;
    }

    public double getNumber(int index) {
        preOp();
        get(index, Consts.LUA_TNUMBER);
        double n = L.getLuaState().toNumber(-1);
        L.getLuaState().pop(1);
        postOp();
        return n;
    }

    public boolean isNumber(String name) {
        preOp();
        int type = getType(name);
        postOp();
        return type == Consts.LUA_TNUMBER;
    }

    public boolean isNumber(int index) {
        preOp();
        int type = getType(index);
        postOp();
        return type == Consts.LUA_TNUMBER;
    }

    public void put(String name, String s) {
        preOp();
        try (var allocator = Allocator.ofPooled()) {
            L.getLuaState().pushString(new PNIString(allocator, s));
        }
        set(name);
        postOp();
    }

    public void set(int index, String s) {
        preOp();
        L.getLuaState().pushInteger(index);
        try (var allocator = Allocator.ofPooled()) {
            L.getLuaState().pushString(new PNIString(allocator, s));
        }
        L.getLuaState().setTable(getTableIndexForSetTable());
        postOp();
    }

    public String getString(String name) {
        preOp();
        get(name, Consts.LUA_TSTRING);
        var str = L.getLuaState().toString(-1).toString();
        L.getLuaState().pop(1);
        postOp();
        return str;
    }

    public String getString(int index) {
        preOp();
        get(index, Consts.LUA_TSTRING);
        var str = L.getLuaState().toString(-1).toString();
        L.getLuaState().pop(1);
        postOp();
        return str;
    }

    public boolean isString(String name) {
        preOp();
        int type = getType(name);
        postOp();
        return type == Consts.LUA_TSTRING;
    }

    public boolean isString(int index) {
        preOp();
        int type = getType(index);
        postOp();
        return type == Consts.LUA_TSTRING;
    }

    public void remove(String name) {
        preOp();
        L.getLuaState().pushNil();
        set(name);
        postOp();
    }

    public void remove(int index) {
        preOp();
        L.getLuaState().pushInteger(index);
        L.getLuaState().pushNil();
        L.getLuaState().setTable(getTableIndexForSetTable());
        postOp();
    }

    public boolean isNil(String name) {
        preOp();
        int type = getType(name);
        postOp();
        return type == Consts.LUA_TNIL;
    }

    public boolean isNil(int index) {
        preOp();
        int type = getType(index);
        postOp();
        return type == Consts.LUA_TNIL;
    }

    public void put(String name, boolean b) {
        preOp();
        L.getLuaState().pushBoolean(b);
        set(name);
        postOp();
    }

    public void set(int index, boolean b) {
        preOp();
        L.getLuaState().pushInteger(index);
        L.getLuaState().pushBoolean(b);
        L.getLuaState().setTable(getTableIndexForSetTable());
        postOp();
    }

    public boolean getBoolean(String name) {
        preOp();
        get(name, Consts.LUA_TBOOLEAN);
        boolean b = L.getLuaState().toBoolean(-1);
        L.getLuaState().pop(1);
        postOp();
        return b;
    }

    public boolean getBoolean(int index) {
        preOp();
        get(index, Consts.LUA_TBOOLEAN);
        boolean b = L.getLuaState().toBoolean(-1);
        L.getLuaState().pop(1);
        postOp();
        return b;
    }

    public boolean isBoolean(String name) {
        preOp();
        int type = getType(name);
        postOp();
        return type == Consts.LUA_TBOOLEAN;
    }

    public boolean isBoolean(int index) {
        preOp();
        int type = getType(index);
        postOp();
        return type == Consts.LUA_TBOOLEAN;
    }

    public void put(String name, MemorySegment data) {
        preOp();
        L.getLuaState().pushLightUserData(data);
        set(name);
        postOp();
    }

    public void set(int index, MemorySegment data) {
        preOp();
        L.getLuaState().pushInteger(index);
        L.getLuaState().pushLightUserData(data);
        L.getLuaState().setTable(getTableIndexForSetTable());
        postOp();
    }

    public MemorySegment getLightUserData(String name) {
        preOp();
        get(name, Consts.LUA_TLIGHTUSERDATA);
        MemorySegment mem = L.getLuaState().toUserData(-1);
        L.getLuaState().pop(1);
        postOp();
        return mem;
    }

    public MemorySegment getLightUserData(int index) {
        preOp();
        get(index, Consts.LUA_TLIGHTUSERDATA);
        MemorySegment mem = L.getLuaState().toUserData(-1);
        L.getLuaState().pop(1);
        postOp();
        return mem;
    }

    public boolean isLightUserData(String name) {
        preOp();
        int type = getType(name);
        postOp();
        return type == Consts.LUA_TLIGHTUSERDATA;
    }

    public boolean isLightUserData(int index) {
        preOp();
        int type = getType(index);
        postOp();
        return type == Consts.LUA_TLIGHTUSERDATA;
    }

    public void put(String name, LuaJNUserData data) {
        preOp();
        data.__push(L);
        set(name);
        postOp();
    }

    public void set(int index, LuaJNUserData data) {
        preOp();
        L.getLuaState().pushInteger(index);
        data.__push(L);
        L.getLuaState().setTable(getTableIndexForSetTable());
        postOp();
    }

    public LuaJNUserData getUserData(String name) {
        preOp();
        get(name, Consts.LUA_TUSERDATA);
        MemorySegment mem = L.getLuaState().toUserData(-1);
        var tmpVar = L.storeTemporaryVariable();
        postOp();
        return new LuaJNUserData(L, tmpVar, mem);
    }

    public LuaJNUserData getUserData(int index) {
        preOp();
        get(index, Consts.LUA_TUSERDATA);
        MemorySegment mem = L.getLuaState().toUserData(-1);
        var tmpVar = L.storeTemporaryVariable();
        postOp();
        return new LuaJNUserData(L, tmpVar, mem);
    }

    public MemorySegment getUserDataMemory(String name) {
        preOp();
        get(name, Consts.LUA_TUSERDATA);
        MemorySegment mem = L.getLuaState().toUserData(-1);
        L.getLuaState().pop(1);
        postOp();
        return mem;
    }

    public MemorySegment getUserDataMemory(int index) {
        preOp();
        get(index, Consts.LUA_TUSERDATA);
        MemorySegment mem = L.getLuaState().toUserData(-1);
        L.getLuaState().pop(1);
        postOp();
        return mem;
    }

    public boolean isUserData(String name) {
        preOp();
        int type = getType(name);
        postOp();
        return type == Consts.LUA_TUSERDATA;
    }

    public boolean isUserData(int index) {
        preOp();
        int type = getType(index);
        postOp();
        return type == Consts.LUA_TUSERDATA;
    }

    public void put(String name, CallSite<LuaState> func) {
        preOp();
        L.getLuaState().pushFunction(func);
        set(name);
        postOp();
    }

    public void set(int index, CallSite<LuaState> func) {
        preOp();
        L.getLuaState().pushInteger(index);
        L.getLuaState().pushFunction(func);
        L.getLuaState().setTable(getTableIndexForSetTable());
        postOp();
    }

    public void put(String name, PNIFunc<?> func) {
        preOp();
        PNIString.temporary("PNIFunc", s -> {
            L.getLuaState().pushUserData(func.MEMORY, PNIFunc.LAYOUT.byteSize(), s);
        });
        set(name);
        postOp();
    }

    public void set(int index, PNIFunc<?> func) {
        preOp();
        L.getLuaState().pushInteger(index);
        PNIString.temporary("PNIFunc", s -> {
            L.getLuaState().pushUserData(func.MEMORY, PNIFunc.LAYOUT.byteSize(), s);
        });
        L.getLuaState().setTable(getTableIndexForSetTable());
        postOp();
    }

    public void put(String name, PNIRef<?> ref) {
        preOp();
        PNIString.temporary("PNIRef", s -> {
            L.getLuaState().pushUserData(ref.MEMORY, PNIRef.LAYOUT.byteSize(), s);
        });
        set(name);
        postOp();
    }

    public void set(int index, PNIRef<?> ref) {
        preOp();
        L.getLuaState().pushInteger(index);
        PNIString.temporary("PNIRef", s -> {
            L.getLuaState().pushUserData(ref.MEMORY, PNIRef.LAYOUT.byteSize(), s);
        });
        L.getLuaState().setTable(getTableIndexForSetTable());
        postOp();
    }

    public void put(String name, LuaJNTable table) {
        preOp();
        table.__push(L);
        set(name);
        postOp();
    }

    public void set(int index, LuaJNTable table) {
        preOp();
        L.getLuaState().pushInteger(index);
        table.__push(L);
        L.getLuaState().setTable(getTableIndexForSetTable());
        postOp();
    }

    public LuaJNTable getTable(String name) {
        preOp();
        get(name, Consts.LUA_TTABLE);
        var tmpVar = L.storeTemporaryVariable();
        postOp();
        return new LuaJNTable(L, -1, tmpVar);
    }

    public LuaJNTable getTable(int index) {
        preOp();
        get(index, Consts.LUA_TTABLE);
        var tmpVar = L.storeTemporaryVariable();
        postOp();
        return new LuaJNTable(L, -1, tmpVar);
    }

    public boolean isTable(String name) {
        preOp();
        int type = getType(name);
        postOp();
        return type == Consts.LUA_TTABLE;
    }

    public boolean isTable(int index) {
        preOp();
        int type = getType(index);
        postOp();
        return type == Consts.LUA_TTABLE;
    }

    public LuaJNFunction getFunction(String name) {
        preOp();
        get(name, Consts.LUA_TFUNCTION);
        var tmpVar = L.storeTemporaryVariable();
        postOp();
        return new LuaJNFunction(L, tmpVar);
    }

    public LuaJNFunction getFunction(int index) {
        preOp();
        get(index, Consts.LUA_TFUNCTION);
        var tmpVar = L.storeTemporaryVariable();
        postOp();
        return new LuaJNFunction(L, tmpVar);
    }

    public boolean isFunction(String name) {
        preOp();
        int type = getType(name);
        postOp();
        return type == Consts.LUA_TFUNCTION;
    }

    public boolean isFunction(int index) {
        preOp();
        int type = getType(index);
        postOp();
        return type == Consts.LUA_TFUNCTION;
    }

    public void put(String name, LuaJNState coroutine) {
        preOp();
        coroutine.__push(L);
        set(name);
        postOp();
    }

    public void set(int index, LuaJNState coroutine) {
        preOp();
        L.getLuaState().pushInteger(index);
        coroutine.__push(L);
        L.getLuaState().setTable(getTableIndexForSetTable());
        postOp();
    }

    public LuaJNState getCoroutine(String name, Allocator allocator) {
        preOp();
        get(name, Consts.LUA_TTHREAD);
        var luaState = L.getLuaState().toThread(-1, allocator);
        var tmpVar = L.storeTemporaryVariable();
        postOp();
        return new LuaJNState(luaState, L, tmpVar);
    }

    public LuaJNState getCoroutine(int index, Allocator allocator) {
        preOp();
        get(index, Consts.LUA_TTHREAD);
        var luaState = L.getLuaState().toThread(-1, allocator);
        var tmpVar = L.storeTemporaryVariable();
        postOp();
        return new LuaJNState(luaState, L, tmpVar);
    }

    public boolean isCoroutine(String name) {
        preOp();
        int type = getType(name);
        postOp();
        return type == Consts.LUA_TTHREAD;
    }

    public boolean isCoroutine(int index) {
        preOp();
        int type = getType(index);
        postOp();
        return type == Consts.LUA_TTHREAD;
    }

    public Object get(String name) {
        return get(name, Allocator.ofDummy());
    }

    public Object get(String name, Allocator allocator) {
        preOp();
        get(name, -1);
        var res = LuaJNative.formatResult(L, allocator);
        postOp();
        return res;
    }

    public Object get(int index) {
        return get(index, Allocator.ofDummy());
    }

    public Object get(int index, Allocator allocator) {
        preOp();
        get(index, -1);
        var res = LuaJNative.formatResult(L, allocator);
        postOp();
        return res;
    }

    public interface ForEachHandler {
        boolean handle(String key, Object value);
    }

    public void foreach(ForEachHandler handler) {
        foreach(Allocator.ofDummy(), handler);
    }

    public void foreach(Allocator allocator, ForEachHandler handler) {
        __push(L);
        L.getLuaState().pushNil();
        while (L.getLuaState().next(-2)) {
            boolean doContinue = handler.handle(
                L.getLuaState().toString(-2).toString(),
                LuaJNative.formatResult(L, allocator));

            if (!doContinue) {
                L.getLuaState().pop(1);
                break;
            }
        }
        L.getLuaState().pop(1); // pop the table
    }

    public void forEach(BiConsumer<String, Object> handler) {
        forEach(Allocator.ofDummy(), handler);
    }

    public void forEach(Allocator allocator, BiConsumer<String, Object> handler) {
        foreach(allocator, (k, v) -> {
            handler.accept(k, v);
            return true;
        });
    }

    public Set<String> keySet() {
        var set = new HashSet<String>();
        forEach(Allocator.ofPooled(), (k, v) -> {
            set.add(k);
            if (v instanceof AutoCloseable) {
                try {
                    ((AutoCloseable) v).close();
                } catch (Exception ignore) {
                }
            }
        });
        return set;
    }
}
