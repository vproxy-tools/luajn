#include "io_vproxy_luajn_n_LuaLib.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLib_openLibs(LuaState * _L) {
    lua_State *L = _L->L;
    luaL_openlibs(L);
    JavaCritical_io_vproxy_luajn_n_LuaLib_openLuaJN(_L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLib_openLuaJN(LuaState * _L) {
    lua_State *L = _L->L;
    luajn_register_module(L, "luajn");
    lua_pushcfunction(L, luajn_PNIFuncInvoke);
    lua_setfield(L, -2, "upcall");
    lua_pushcfunction(L, luajn_PNIFuncRelease);
    lua_setfield(L, -2, "release_func");
    lua_pushcfunction(L, luajn_PNIRefRelease);
    lua_setfield(L, -2, "release_ref");
    lua_pop(L, 1);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLib_openBase(LuaState * _L) {
    lua_State *L = _L->L;
    luaopen_base(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLib_openMath(LuaState * _L) {
    lua_State *L = _L->L;
    luaopen_math(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLib_openString(LuaState * _L) {
    lua_State *L = _L->L;
    luaopen_string(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLib_openTable(LuaState * _L) {
    lua_State *L = _L->L;
    luaopen_table(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLib_openIO(LuaState * _L) {
    lua_State *L = _L->L;
    luaopen_io(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLib_openOS(LuaState * _L) {
    lua_State *L = _L->L;
    luaopen_os(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLib_openPackage(LuaState * _L) {
    lua_State *L = _L->L;
    luaopen_package(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLib_openDebug(LuaState * _L) {
    lua_State *L = _L->L;
    luaopen_debug(L);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni 21.0.0.8
// sha256:82c7c3bc766aefd278dfc8657784b9eb6739996aab3b6bb954bad67cc44e8951
