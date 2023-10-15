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

    private static final MethodHandle openBitMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLibLuaJIT_openBit", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openBit(io.vproxy.luajn.n.LuaState _L) {
        try {
            openBitMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openJITMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLibLuaJIT_openJIT", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openJIT(io.vproxy.luajn.n.LuaState _L) {
        try {
            openJITMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openFFIMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLibLuaJIT_openFFI", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openFFI(io.vproxy.luajn.n.LuaState _L) {
        try {
            openFFIMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openStringBufferMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLibLuaJIT_openStringBuffer", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openStringBuffer(io.vproxy.luajn.n.LuaState _L) {
        try {
            openStringBufferMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }
}
// metadata.generator-version: pni 21.0.0.17
// sha256:b30cca6f9097c81eed95c6e2c50b6cef012472870850a95ad748ee47eabf1c68
