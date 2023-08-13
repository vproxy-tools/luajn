#include "io_vproxy_luajn_n_Lua.h"
#include <stdio.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT LuaState * JNICALL JavaCritical_io_vproxy_luajn_n_Lua_newState(LuaState * return_) {
    lua_State *L = luaL_newstate();
    return JavaCritical_io_vproxy_luajn_n_Lua_initState(L, return_);
}

JNIEXPORT LuaState * JNICALL JavaCritical_io_vproxy_luajn_n_Lua_newState2(void * allocF, void * ud, LuaState * return_) {
    lua_State *L = lua_newstate((lua_Alloc) allocF, ud);
    return JavaCritical_io_vproxy_luajn_n_Lua_initState(L, return_);
}

JNIEXPORT LuaState * JNICALL JavaCritical_io_vproxy_luajn_n_Lua_initState(void * _L, LuaState * return_) {
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
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_Lua_atPanic(LuaState * _L, void * func) {
    lua_State *L = _L->L;
    return lua_atpanic(L, (lua_CFunction) func);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Lua_dump(LuaState * _L, void * writer, void * data) {
    lua_State *L = _L->L;
    #if LUA_VERSION_NUM >= 503
    return lua_dump(L, (lua_Writer) writer, data, 0);
    #else
    return lua_dump(L, (lua_Writer) writer, data);
    #endif
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Lua_load(LuaState * _L, void * reader, void * data, char * chunkName) {
    lua_State *L = _L->L;
    #if LUA_VERSION_NUM >= 502
    return lua_load(L, (lua_Reader) reader, data, chunkName, "bt");
    #else
    return lua_load(L, (lua_Reader) reader, data, chunkName);
    #endif
}

JNIEXPORT LuaState * JNICALL JavaCritical_io_vproxy_luajn_n_Lua_newThread(LuaState * _L, LuaState * return_) {
    lua_State *L = _L->L;
    lua_State *T = lua_newthread(L);
    return_->L = T;
    return return_;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Lua_loadFile(LuaState * _L, char * filename) {
    lua_State *L = _L->L;
    return luaL_loadfile(L, filename);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Lua_loadString(LuaState * _L, char * s) {
    lua_State *L = _L->L;
    return luaL_loadstring(L, s);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Lua_gc(LuaState * _L, int32_t what, int32_t data) {
    lua_State *L = _L->L;
    return lua_gc(L, what, data);
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_Lua_getAllocF(LuaState * _L, void * _ud) {
    lua_State *L = _L->L;
    void** ud = (void**) _ud;
    return lua_getallocf(L, ud);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_Lua_getMetaTable(LuaState * _L, char * tname) {
    lua_State *L = _L->L;
    luaL_getmetatable(L, tname);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_Lua_registerModule(LuaState * _L, char * libname) {
    lua_State *L = _L->L;
    luajn_register_module(L, libname);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Lua_resume(LuaState * _L, int32_t narg) {
    lua_State *L = _L->L;
    #if LUA_VERSION_NUM >= 504
    int nresults;
    return lua_resume(L, NULL, narg, &nresults);
    #elif LUA_VERSION_NUM >= 502
    return lua_resume(L, NULL, narg);
    #else
    return lua_resume(L, narg);
    #endif
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_Lua_setAllocF(LuaState * _L, void * allocF, void * ud) {
    lua_State *L = _L->L;
    lua_setallocf(L, (lua_Alloc) allocF, ud);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Lua_status(LuaState * _L) {
    lua_State *L = _L->L;
    return lua_status(L);
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_luajn_n_Lua_typename(LuaState * _L, int32_t tp) {
    lua_State *L = _L->L;
    return (char*) lua_typename(L, tp);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Lua_upValueIndex(int32_t index) {
    return lua_upvalueindex(index);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_Lua_xmove(LuaState * _from, LuaState * _to, int32_t n) {
    lua_State *from = _from->L;
    lua_State *to = _to->L;
    return lua_xmove(from, to, n);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Lua_version(LuaState * _L) {
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
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni 21.0.0.8
// sha256:51026abd430055d083f32cb40cc06575013e200c995b5ddd46fb97a751f35995
