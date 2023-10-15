package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class LuaLib {
    private LuaLib() {
    }

    private static final LuaLib INSTANCE = new LuaLib();

    public static LuaLib get() {
        return INSTANCE;
    }

    private static final MethodHandle openLibsMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openLibs", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openLibs(io.vproxy.luajn.n.LuaState _L) {
        try {
            openLibsMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openLuaJNMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openLuaJN", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openLuaJN(io.vproxy.luajn.n.LuaState _L) {
        try {
            openLuaJNMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openBaseMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openBase", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openBase(io.vproxy.luajn.n.LuaState _L) {
        try {
            openBaseMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openMathMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openMath", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openMath(io.vproxy.luajn.n.LuaState _L) {
        try {
            openMathMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openStringMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openString", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openString(io.vproxy.luajn.n.LuaState _L) {
        try {
            openStringMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openTableMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openTable", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openTable(io.vproxy.luajn.n.LuaState _L) {
        try {
            openTableMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openIOMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openIO", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openIO(io.vproxy.luajn.n.LuaState _L) {
        try {
            openIOMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openOSMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openOS", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openOS(io.vproxy.luajn.n.LuaState _L) {
        try {
            openOSMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openPackageMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openPackage", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openPackage(io.vproxy.luajn.n.LuaState _L) {
        try {
            openPackageMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openDebugMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openDebug", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openDebug(io.vproxy.luajn.n.LuaState _L) {
        try {
            openDebugMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }
}
// metadata.generator-version: pni 21.0.0.17
// sha256:ba1fc4d2b029f7e6ba52646e574d61594e0b4efb461e3cf6ae797937efc60b20
