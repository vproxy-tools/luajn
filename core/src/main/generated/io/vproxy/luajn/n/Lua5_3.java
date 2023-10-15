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

    private static final MethodHandle isyieldableMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_Lua5_3_isyieldable", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void isyieldable(io.vproxy.luajn.n.LuaState _L) {
        try {
            isyieldableMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }
}
// metadata.generator-version: pni 21.0.0.17
// sha256:459a8f304687ec1848828effaa676f928d1b3953a35a0ac9caa83519fc0ca932
