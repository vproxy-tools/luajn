#include "io_vproxy_luajn_n_LuaDebugFuncs.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_sizeofLuaDebug(void) {
    return sizeof(struct lua_Debug);
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getHook(LuaState * _L) {
    lua_State *L = _L->L;
    return lua_gethook(L);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getHookCount(LuaState * _L) {
    lua_State *L = _L->L;
    return lua_gethookcount(L);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getHookMask(LuaState * _L) {
    lua_State *L = _L->L;
    return lua_gethookmask(L);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getInfo(LuaState * _L, char * what, lua_Debug * ar) {
    lua_State *L = _L->L;
    return lua_getinfo(L, what, ar);
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getLocal(LuaState * _L, lua_Debug * ar, int32_t n) {
    lua_State *L = _L->L;
    return (char*) lua_getlocal(L, ar, n);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getStack(LuaState * _L, int32_t level, lua_Debug * ar) {
    lua_State *L = _L->L;
    return lua_getstack(L, level, ar);
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getUpValue(LuaState * _L, int32_t funcIndex, int32_t n) {
    lua_State *L = _L->L;
    return (char*) lua_getupvalue(L, funcIndex, n);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_setHook(LuaState * _L, void * hookF, int32_t mask, int32_t count) {
    lua_State *L = _L->L;
    #if LUA_VERSION_NUM >= 503
    lua_sethook(L, (lua_Hook) hookF, mask, count);
    return 1;
    #else
    return lua_sethook(L, (lua_Hook) hookF, mask, count);
    #endif
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_setLocal(LuaState * _L, lua_Debug * ar, int32_t n) {
    lua_State *L = _L->L;
    return (char*) lua_setlocal(L, ar, n);
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_setUpValue(LuaState * _L, int32_t funcIndex, int32_t n) {
    lua_State *L = _L->L;
    return (char*) lua_setupvalue(L, funcIndex, n);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni 21.0.0.8
// sha256:4119881579cb6d2eac1e7a1252ffb94a3b77bf7c0fc135fc3200cb941faa3766
