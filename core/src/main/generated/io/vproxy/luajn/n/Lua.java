package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Lua {
    private Lua() {
    }

    private static final Lua INSTANCE = new Lua();

    public static Lua get() {
        return INSTANCE;
    }

    private static final MethodHandle newStateMH = PanamaUtils.lookupPNICriticalFunction(true, io.vproxy.luajn.n.LuaState.LAYOUT.getClass(), "JavaCritical_io_vproxy_luajn_n_Lua_newState", MemorySegment.class /* return */);

    public io.vproxy.luajn.n.LuaState newState(Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) newStateMH.invokeExact(ALLOCATOR.allocate(io.vproxy.luajn.n.LuaState.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.luajn.n.LuaState(RESULT);
    }

    private static final MethodHandle newState2MH = PanamaUtils.lookupPNICriticalFunction(true, io.vproxy.luajn.n.LuaState.LAYOUT.getClass(), "JavaCritical_io_vproxy_luajn_n_Lua_newState2", MemorySegment.class /* allocF */, MemorySegment.class /* ud */, MemorySegment.class /* return */);

    public io.vproxy.luajn.n.LuaState newState2(MemorySegment allocF, MemorySegment ud, Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) newState2MH.invokeExact((MemorySegment) (allocF == null ? MemorySegment.NULL : allocF), (MemorySegment) (ud == null ? MemorySegment.NULL : ud), ALLOCATOR.allocate(io.vproxy.luajn.n.LuaState.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.luajn.n.LuaState(RESULT);
    }

    private static final MethodHandle initStateMH = PanamaUtils.lookupPNICriticalFunction(true, io.vproxy.luajn.n.LuaState.LAYOUT.getClass(), "JavaCritical_io_vproxy_luajn_n_Lua_initState", MemorySegment.class /* _L */, MemorySegment.class /* return */);

    public io.vproxy.luajn.n.LuaState initState(MemorySegment _L, Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) initStateMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L), ALLOCATOR.allocate(io.vproxy.luajn.n.LuaState.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.luajn.n.LuaState(RESULT);
    }

    private static final MethodHandle atPanicMH = PanamaUtils.lookupPNICriticalFunction(true, MemorySegment.class, "JavaCritical_io_vproxy_luajn_n_Lua_atPanic", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, MemorySegment.class /* func */);

    public MemorySegment atPanic(io.vproxy.luajn.n.LuaState _L, MemorySegment func) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) atPanicMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (func == null ? MemorySegment.NULL : func));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle dumpMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_Lua_dump", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, MemorySegment.class /* writer */, MemorySegment.class /* data */);

    public int dump(io.vproxy.luajn.n.LuaState _L, MemorySegment writer, MemorySegment data) {
        int RESULT;
        try {
            RESULT = (int) dumpMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (writer == null ? MemorySegment.NULL : writer), (MemorySegment) (data == null ? MemorySegment.NULL : data));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle loadMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_Lua_load", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, MemorySegment.class /* reader */, MemorySegment.class /* data */, String.class /* chunkName */);

    public int load(io.vproxy.luajn.n.LuaState _L, MemorySegment reader, MemorySegment data, PNIString chunkName) {
        int RESULT;
        try {
            RESULT = (int) loadMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (reader == null ? MemorySegment.NULL : reader), (MemorySegment) (data == null ? MemorySegment.NULL : data), (MemorySegment) (chunkName == null ? MemorySegment.NULL : chunkName.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle newThreadMH = PanamaUtils.lookupPNICriticalFunction(true, io.vproxy.luajn.n.LuaState.LAYOUT.getClass(), "JavaCritical_io_vproxy_luajn_n_Lua_newThread", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, MemorySegment.class /* return */);

    public io.vproxy.luajn.n.LuaState newThread(io.vproxy.luajn.n.LuaState _L, Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) newThreadMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), ALLOCATOR.allocate(io.vproxy.luajn.n.LuaState.LAYOUT.byteSize()));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.luajn.n.LuaState(RESULT);
    }

    private static final MethodHandle loadFileMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_Lua_loadFile", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, String.class /* filename */);

    public int loadFile(io.vproxy.luajn.n.LuaState _L, PNIString filename) {
        int RESULT;
        try {
            RESULT = (int) loadFileMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (filename == null ? MemorySegment.NULL : filename.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle loadStringMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_Lua_loadString", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, String.class /* s */);

    public int loadString(io.vproxy.luajn.n.LuaState _L, PNIString s) {
        int RESULT;
        try {
            RESULT = (int) loadStringMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (s == null ? MemorySegment.NULL : s.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle gcMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_Lua_gc", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, int.class /* what */, int.class /* data */);

    public int gc(io.vproxy.luajn.n.LuaState _L, int what, int data) {
        int RESULT;
        try {
            RESULT = (int) gcMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), what, data);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle getAllocFMH = PanamaUtils.lookupPNICriticalFunction(true, MemorySegment.class, "JavaCritical_io_vproxy_luajn_n_Lua_getAllocF", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, MemorySegment.class /* _ud */);

    public MemorySegment getAllocF(io.vproxy.luajn.n.LuaState _L, MemorySegment _ud) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) getAllocFMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (_ud == null ? MemorySegment.NULL : _ud));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle getMetaTableMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_Lua_getMetaTable", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, String.class /* tname */);

    public void getMetaTable(io.vproxy.luajn.n.LuaState _L, PNIString tname) {
        try {
            getMetaTableMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (tname == null ? MemorySegment.NULL : tname.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle registerModuleMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_Lua_registerModule", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, String.class /* libname */);

    public void registerModule(io.vproxy.luajn.n.LuaState _L, PNIString libname) {
        try {
            registerModuleMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (libname == null ? MemorySegment.NULL : libname.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle resumeMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_Lua_resume", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, int.class /* narg */);

    public int resume(io.vproxy.luajn.n.LuaState _L, int narg) {
        int RESULT;
        try {
            RESULT = (int) resumeMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), narg);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle setAllocFMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_Lua_setAllocF", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, MemorySegment.class /* allocF */, MemorySegment.class /* ud */);

    public void setAllocF(io.vproxy.luajn.n.LuaState _L, MemorySegment allocF, MemorySegment ud) {
        try {
            setAllocFMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), (MemorySegment) (allocF == null ? MemorySegment.NULL : allocF), (MemorySegment) (ud == null ? MemorySegment.NULL : ud));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle statusMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_Lua_status", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public int status(io.vproxy.luajn.n.LuaState _L) {
        int RESULT;
        try {
            RESULT = (int) statusMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle typenameMH = PanamaUtils.lookupPNICriticalFunction(true, String.class, "JavaCritical_io_vproxy_luajn_n_Lua_typename", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */, int.class /* tp */);

    public PNIString typename(io.vproxy.luajn.n.LuaState _L, int tp) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) typenameMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY), tp);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }

    private static final MethodHandle upValueIndexMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_Lua_upValueIndex", int.class /* index */);

    public int upValueIndex(int index) {
        int RESULT;
        try {
            RESULT = (int) upValueIndexMH.invokeExact(index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle xmoveMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_n_Lua_xmove", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _from */, io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _to */, int.class /* n */);

    public void xmove(io.vproxy.luajn.n.LuaState _from, io.vproxy.luajn.n.LuaState _to, int n) {
        try {
            xmoveMH.invokeExact((MemorySegment) (_from == null ? MemorySegment.NULL : _from.MEMORY), (MemorySegment) (_to == null ? MemorySegment.NULL : _to.MEMORY), n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle versionMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_Lua_version", io.vproxy.luajn.n.LuaState.LAYOUT.getClass() /* _L */);

    public int version(io.vproxy.luajn.n.LuaState _L) {
        int RESULT;
        try {
            RESULT = (int) versionMH.invokeExact((MemorySegment) (_L == null ? MemorySegment.NULL : _L.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }
}
// metadata.generator-version: pni 21.0.0.8
// sha256:1552a13d28ae4dc43bedc11279940bcc2f455c647fc2b56dfcae7747a2ccd83f
