package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@SuppressWarnings("unused")
@Function
@Include({
    "<lua.h>",
    "<lauxlib.h>",
    "<lualib.h>",
})
interface PNILua {
    @Impl(
        // language="c"
        c = """
            lua_State *L = luaL_newstate();
            return JavaCritical_io_vproxy_luajn_n_Lua_initState(L, return_);
            """
    )
    @Trivial
    @Critical
    PNILuaState newState();

    @Impl(
        // language="c"
        c = """
            lua_State *L = lua_newstate((lua_Alloc) allocF, ud);
            return JavaCritical_io_vproxy_luajn_n_Lua_initState(L, return_);
            """
    )
    @Trivial
    @Critical
    PNILuaState newState2(MemorySegment allocF, MemorySegment ud);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L;
            if (L == NULL) {
                return NULL;
            }
            if (!luaL_newmetatable(L, "PNIFunc")) {
                lua_close(L);
                return NULL;
            }
            lua_pushliteral(L, "__gc");
            lua_pushcfunction(L, luajn_PNIFuncRelease);
            lua_settable(L, -3);
            lua_pop(L, 1);
                        
            if (!luaL_newmetatable(L, "PNIRef")) {
                lua_close(L);
                return NULL;
            }
            lua_pushliteral(L, "__gc");
            lua_pushcfunction(L, luajn_PNIRefRelease);
            lua_settable(L, -3);
            lua_pop(L, 1);
                        
            return_->L = L;
            return return_;
            """
    )
    @Trivial
    @Critical
    PNILuaState initState(MemorySegment _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_atpanic(L, (lua_CFunction) func);
            """
    )
    @Trivial
    @Critical
    MemorySegment atPanic(PNILuaState _L, MemorySegment func);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            #if LUA_VERSION_NUM >= 503
            return lua_dump(L, (lua_Writer) writer, data, 0);
            #else
            return lua_dump(L, (lua_Writer) writer, data);
            #endif
            """
    )
    @Trivial
    @Critical
    int dump(PNILuaState _L, MemorySegment writer, MemorySegment data);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            #if LUA_VERSION_NUM >= 502
            return lua_load(L, (lua_Reader) reader, data, chunkName, "bt");
            #else
            return lua_load(L, (lua_Reader) reader, data, chunkName);
            #endif
            """
    )
    @Trivial
    @Critical
    int load(PNILuaState _L, MemorySegment reader, MemorySegment data, String chunkName);

    @Impl(
        include = "<stdio.h>",
        // language="c"
        c = """
            lua_State *L = _L->L;
            lua_State *T = lua_newthread(L);
            return_->L = T;
            return return_;
            """
    )
    @Trivial
    @Critical
    PNILuaState newThread(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return luaL_loadfile(L, filename);
            """
    )
    @Trivial
    @Critical
    int loadFile(PNILuaState _L, String filename);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return luaL_loadstring(L, s);
            """
    )
    @Trivial
    @Critical
    int loadString(PNILuaState _L, String s);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_gc(L, what, data);
            """
    )
    @Trivial
    @Critical
    int gc(PNILuaState _L, int what, int data);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            void** ud = (void**) _ud;
            return lua_getallocf(L, ud);
            """
    )
    @Trivial
    @Critical
    MemorySegment getAllocF(PNILuaState _L, MemorySegment _ud);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaL_getmetatable(L, tname);
            """
    )
    @Trivial
    @Critical
    void getMetaTable(PNILuaState _L, String tname);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luajn_register_module(L, libname);
            """
    )
    @Trivial
    @Critical
    void registerModule(PNILuaState _L, String libname);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            #if LUA_VERSION_NUM >= 504
            int nresults;
            return lua_resume(L, NULL, narg, &nresults);
            #elif LUA_VERSION_NUM >= 502
            return lua_resume(L, NULL, narg);
            #else
            return lua_resume(L, narg);
            #endif
            """
    )
    @Trivial
    @Critical
    int resume(PNILuaState _L, int narg);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            lua_setallocf(L, (lua_Alloc) allocF, ud);
            """
    )
    @Critical
    @Trivial
    void setAllocF(PNILuaState _L, MemorySegment allocF, MemorySegment ud);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_status(L);
            """
    )
    @Critical
    @Trivial
    int status(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return (char*) lua_typename(L, tp);
            """
    )
    @Trivial
    @Critical
    String typename(PNILuaState _L, int tp);

    @Impl(
        // language="c"
        c = """
            return lua_upvalueindex(index);
            """
    )
    @Trivial
    @Critical
    int upValueIndex(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *from = _from->L;
            lua_State *to = _to->L;
            return lua_xmove(from, to, n);
            """
    )
    @Trivial
    @Critical
    void xmove(PNILuaState _from, PNILuaState _to, int n);

// it's unsafe to use yield in Java
//    @Impl(
//        // language="c"
//        c = """
//            lua_State *L = _L->L;
//            return lua_yield(L, nresults);
//            """
//    )
//    @Trivial
//    @Critical
//    int yield(PNILuaState _L, int nresults);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            #if LUA_VERSION_NUM >= 504
            return (int32_t) lua_version(L);
            #elif LUA_VERSION_NUM >= 502 || (LUA_VERSION_NUM == 501 && defined(LUA_OK))
            const lua_Number* ptr = lua_version(L);
            if (ptr == NULL) {
                return LUA_VERSION_NUM;
            }
            lua_Number n = *ptr;
            return n;
            #else
            return LUA_VERSION_NUM;
            #endif
            """
    )
    @Trivial
    @Critical
    int version(PNILuaState _L);
}
