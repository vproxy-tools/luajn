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

    private static final MethodHandle setModeMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_LuaJIT_setMode", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, int.class /* idx */, int.class /* mode */);

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
// metadata.generator-version: pni 21.0.0.8
// sha256:269da573bac3e67439c198093a9324feebeed3c2231a55a54307710d0492b3a5
