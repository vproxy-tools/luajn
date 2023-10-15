package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class LuaJIT {
    private LuaJIT() {
    }

    private static final LuaJIT INSTANCE = new LuaJIT();

    public static LuaJIT get() {
        return INSTANCE;
    }

    private static final MethodHandle setModeMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_LuaJIT_setMode", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, int.class /* idx */, int.class /* mode */);

    public int setMode(io.vproxy.luajn.n.LuaState _L, int idx, int mode) {
        int RESULT;
        try {
            RESULT = (int) setModeMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), idx, mode);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }
}
// metadata.generator-version: pni 21.0.0.17
// sha256:eac5064d69b8c49f6005046d44b732e72a40dec9d30d0baa6512289db73a9c35
