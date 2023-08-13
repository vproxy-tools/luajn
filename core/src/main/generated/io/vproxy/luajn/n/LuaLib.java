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

    private static final MethodHandle openLibsMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openLibs", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openLibs(io.vproxy.luajn.n.LuaState _L) {
        try {
            openLibsMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openLuaJNMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openLuaJN", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openLuaJN(io.vproxy.luajn.n.LuaState _L) {
        try {
            openLuaJNMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openBaseMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openBase", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openBase(io.vproxy.luajn.n.LuaState _L) {
        try {
            openBaseMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openMathMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openMath", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openMath(io.vproxy.luajn.n.LuaState _L) {
        try {
            openMathMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openStringMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openString", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openString(io.vproxy.luajn.n.LuaState _L) {
        try {
            openStringMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openTableMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openTable", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openTable(io.vproxy.luajn.n.LuaState _L) {
        try {
            openTableMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openIOMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openIO", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openIO(io.vproxy.luajn.n.LuaState _L) {
        try {
            openIOMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openOSMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openOS", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openOS(io.vproxy.luajn.n.LuaState _L) {
        try {
            openOSMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openPackageMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openPackage", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openPackage(io.vproxy.luajn.n.LuaState _L) {
        try {
            openPackageMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle openDebugMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_LuaLib_openDebug", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public void openDebug(io.vproxy.luajn.n.LuaState _L) {
        try {
            openDebugMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }
}
// metadata.generator-version: pni 21.0.0.8
// sha256:e316476a98a7ce72e706f53fbdb248a2df8f59342ce3009c30deb61a47177158
