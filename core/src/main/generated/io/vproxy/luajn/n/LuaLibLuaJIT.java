package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class LuaLibLuaJIT {
    private LuaLibLuaJIT() {
    }

    private static final LuaLibLuaJIT INSTANCE = new LuaLibLuaJIT();

    public static LuaLibLuaJIT get() {
        return INSTANCE;
    }

    private static final MethodHandle openBitMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLibLuaJIT_openBit", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openBit(io.vproxy.luajn.n.LuaState _L) {
        try {
            openBitMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openJITMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLibLuaJIT_openJIT", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openJIT(io.vproxy.luajn.n.LuaState _L) {
        try {
            openJITMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openFFIMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLibLuaJIT_openFFI", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openFFI(io.vproxy.luajn.n.LuaState _L) {
        try {
            openFFIMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openStringBufferMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLibLuaJIT_openStringBuffer", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openStringBuffer(io.vproxy.luajn.n.LuaState _L) {
        try {
            openStringBufferMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }
}
// metadata.generator-version: pni 21.0.0.8
// sha256:d88a0f49c933e171edba32cda1bcda51dd0f0d720733e49c59aa4844c17ed7af
