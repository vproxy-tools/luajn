package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class LuaDebugFuncs {
    private LuaDebugFuncs() {
    }

    private static final LuaDebugFuncs INSTANCE = new LuaDebugFuncs();

    public static LuaDebugFuncs get() {
        return INSTANCE;
    }

    private static final MethodHandle sizeofLuaDebugMH = PanamaUtils.lookupPNICriticalFunction(true, long.class, "JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_sizeofLuaDebug");

    public long sizeofLuaDebug() {
        long RESULT;
        try {
            RESULT = (long) sizeofLuaDebugMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle getHookMH = PanamaUtils.lookupPNICriticalFunction(true, MemorySegment.class, "JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getHook", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public MemorySegment getHook(io.vproxy.luajn.n.LuaState _L) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) getHookMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle getHookCountMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getHookCount", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public int getHookCount(io.vproxy.luajn.n.LuaState _L) {
        int RESULT;
        try {
            RESULT = (int) getHookCountMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle getHookMaskMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getHookMask", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public int getHookMask(io.vproxy.luajn.n.LuaState _L) {
        int RESULT;
        try {
            RESULT = (int) getHookMaskMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle getInfoMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getInfo", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, String.class /* what */, io.vproxy.luajn.n.LuaDebug.LAYOUT.getClass() /* ar */);

    public int getInfo(io.vproxy.luajn.n.LuaState _L, PNIString what, io.vproxy.luajn.n.LuaDebug ar) {
        int RESULT;
        try {
            RESULT = (int) getInfoMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (what == null ? MemorySegment.NULL : what.MEMORY), (MemorySegment) (ar == null ? MemorySegment.NULL : ar.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle getLocalMH = PanamaUtils.lookupPNICriticalFunction(true, String.class, "JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getLocal", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, io.vproxy.luajn.n.LuaDebug.LAYOUT.getClass() /* ar */, int.class /* n */);

    public PNIString getLocal(io.vproxy.luajn.n.LuaState _L, io.vproxy.luajn.n.LuaDebug ar, int n) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) getLocalMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (ar == null ? MemorySegment.NULL : ar.MEMORY), n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }

    private static final MethodHandle getStackMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getStack", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, int.class /* level */, io.vproxy.luajn.n.LuaDebug.LAYOUT.getClass() /* ar */);

    public int getStack(io.vproxy.luajn.n.LuaState _L, int level, io.vproxy.luajn.n.LuaDebug ar) {
        int RESULT;
        try {
            RESULT = (int) getStackMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), level, (MemorySegment) (ar == null ? MemorySegment.NULL : ar.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle getUpValueMH = PanamaUtils.lookupPNICriticalFunction(true, String.class, "JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getUpValue", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, int.class /* funcIndex */, int.class /* n */);

    public PNIString getUpValue(io.vproxy.luajn.n.LuaState _L, int funcIndex, int n) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) getUpValueMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), funcIndex, n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }

    private static final MethodHandle setHookMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_setHook", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, MemorySegment.class /* hookF */, int.class /* mask */, int.class /* count */);

    public int setHook(io.vproxy.luajn.n.LuaState _L, MemorySegment hookF, int mask, int count) {
        int RESULT;
        try {
            RESULT = (int) setHookMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (hookF == null ? MemorySegment.NULL : hookF), mask, count);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle setLocalMH = PanamaUtils.lookupPNICriticalFunction(true, String.class, "JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_setLocal", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, io.vproxy.luajn.n.LuaDebug.LAYOUT.getClass() /* ar */, int.class /* n */);

    public PNIString setLocal(io.vproxy.luajn.n.LuaState _L, io.vproxy.luajn.n.LuaDebug ar, int n) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) setLocalMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (ar == null ? MemorySegment.NULL : ar.MEMORY), n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }

    private static final MethodHandle setUpValueMH = PanamaUtils.lookupPNICriticalFunction(true, String.class, "JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_setUpValue", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, int.class /* funcIndex */, int.class /* n */);

    public PNIString setUpValue(io.vproxy.luajn.n.LuaState _L, int funcIndex, int n) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) setUpValueMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), funcIndex, n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }
}
// metadata.generator-version: pni 21.0.0.8
// sha256:256c0be1db91d47dce8cf925175f7044d210201890f9f4ad4a49d035a3e3f47d
