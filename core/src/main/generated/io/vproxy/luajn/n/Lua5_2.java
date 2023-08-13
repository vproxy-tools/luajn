package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Lua5_2 {
    private Lua5_2() {
    }

    private static final Lua5_2 INSTANCE = new Lua5_2();

    public static Lua5_2 get() {
        return INSTANCE;
    }

    private static final MethodHandle copyMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_Lua5_2_copy", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, int.class /* fromidx */, int.class /* toidx */);

    public void copy(io.vproxy.luajn.n.LuaState _L, int fromidx, int toidx) {
        try {
            copyMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), fromidx, toidx);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle upValueIdMH = PanamaUtils.lookupPNICriticalFunction(true, MemorySegment.class, "JavaCritical_io_vproxy_luajn_n_Lua5_2_upValueId", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, int.class /* funcindex */, int.class /* n */);

    public MemorySegment upValueId(io.vproxy.luajn.n.LuaState _L, int funcindex, int n) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) upValueIdMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), funcindex, n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle upValueJoinMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_Lua5_2_upValueJoin", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, int.class /* funcindex1 */, int.class /* n1 */, int.class /* funcindex2 */, int.class /* n2 */);

    public void upValueJoin(io.vproxy.luajn.n.LuaState _L, int funcindex1, int n1, int funcindex2, int n2) {
        try {
            upValueJoinMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), funcindex1, n1, funcindex2, n2);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle toNumberXMH = PanamaUtils.lookupPNICriticalFunction(true, double.class, "JavaCritical_io_vproxy_luajn_n_Lua5_2_toNumberX", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, int.class /* index */, MemorySegment.class /* isnum */);

    public double toNumberX(io.vproxy.luajn.n.LuaState _L, int index, IntArray isnum) {
        double RESULT;
        try {
            RESULT = (double) toNumberXMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), index, (MemorySegment) (isnum == null ? MemorySegment.NULL : isnum.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle toIntegerXMH = PanamaUtils.lookupPNICriticalFunction(true, long.class, "JavaCritical_io_vproxy_luajn_n_Lua5_2_toIntegerX", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, int.class /* index */, MemorySegment.class /* isnum */);

    public long toIntegerX(io.vproxy.luajn.n.LuaState _L, int index, IntArray isnum) {
        long RESULT;
        try {
            RESULT = (long) toIntegerXMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), index, (MemorySegment) (isnum == null ? MemorySegment.NULL : isnum.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }
}
// metadata.generator-version: pni 21.0.0.8
// sha256:813d7fe2b8c465ae5b13a48b757f4a5da594dbc3b5391ced861f050425b873c5
