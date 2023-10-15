package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class LuaOpenresty {
    private LuaOpenresty() {
    }

    private static final LuaOpenresty INSTANCE = new LuaOpenresty();

    public static LuaOpenresty get() {
        return INSTANCE;
    }

    private static final MethodHandle getExDataMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), MemorySegment.class, "JavaCritical_io_vproxy_luajn_n_LuaOpenresty_getExData", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public MemorySegment getExData(io.vproxy.luajn.n.LuaState _L) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) getExDataMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle setExDataMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaOpenresty_setExData", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, MemorySegment.class /* exdata */);

    public void setExData(io.vproxy.luajn.n.LuaState _L, MemorySegment exdata) {
        try {
            setExDataMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (exdata == null ? MemorySegment.NULL : exdata));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle getExData2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), MemorySegment.class, "JavaCritical_io_vproxy_luajn_n_LuaOpenresty_getExData2", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public MemorySegment getExData2(io.vproxy.luajn.n.LuaState _L) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) getExData2MH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle setExData2MH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaOpenresty_setExData2", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, MemorySegment.class /* exdata */);

    public void setExData2(io.vproxy.luajn.n.LuaState _L, MemorySegment exdata) {
        try {
            setExData2MH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (exdata == null ? MemorySegment.NULL : exdata));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle resetThreadMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaOpenresty_resetThread", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _th */);

    public void resetThread(io.vproxy.luajn.n.LuaState _L, io.vproxy.luajn.n.LuaState _th) {
        try {
            resetThreadMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (_th == null ? MemorySegment.NULL : _th.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }
}
// metadata.generator-version: pni 21.0.0.17
// sha256:b4e5ddc0e72a7a257932ec2fdc9f1ecf2c8bd3babb7a2877a127450b7524e6ae
