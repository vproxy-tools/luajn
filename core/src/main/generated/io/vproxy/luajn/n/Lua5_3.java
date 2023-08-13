package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Lua5_3 {
    private Lua5_3() {
    }

    private static final Lua5_3 INSTANCE = new Lua5_3();

    public static Lua5_3 get() {
        return INSTANCE;
    }

    private static final MethodHandle isyieldableMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_Lua5_3_isyieldable", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void isyieldable(io.vproxy.luajn.n.LuaState _L) {
        try {
            isyieldableMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }
}
// metadata.generator-version: pni 21.0.0.8
// sha256:d3263fb76560c0749dbfdd7cc8f7cb3da67bb5e2abe5a08a5601926ecf16aadc
