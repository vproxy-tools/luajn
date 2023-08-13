package io.vproxy.luajn;

import io.vproxy.luajn.n.Lua;
import io.vproxy.pni.PNIString;

import java.lang.foreign.MemorySegment;

public class LuaJNUserData implements AutoCloseable {
    private final LuaJNState L;
    private final MemorySegment registryName;
    public final MemorySegment MEMORY;

    public LuaJNUserData(LuaJNState l, MemorySegment registryName, MemorySegment MEMORY) {
        L = l;
        this.registryName = registryName;
        this.MEMORY = MEMORY;
    }

    public void __push(LuaJNState L) {
        L.pushTemporaryVariable(registryName);
        if (!L.getLuaState().isUserdata(-1)) {
            var typename = Lua.get().typename(L.getLuaState(), L.getLuaState().type(-1)).toString();
            L.getLuaState().pop(1);
            throw new LuaCallException("stack top element is not userdata: " + typename);
        }
    }

    @Override
    public void close() {
        L.removeTemporaryVariable(registryName);
    }

    public void setMetaTable(String name) {
        __push(L);
        PNIString.temporary(name, s -> {
            Lua.get().getMetaTable(L.getLuaState(), s);
        });
        L.getLuaState().setMetaTable(-2);
        L.getLuaState().pop(1);
    }
}
