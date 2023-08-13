#include "io_vproxy_luajn_n_LuaOpenresty.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_LuaOpenresty_getExData(LuaState * _L) {
    lua_State *L = _L->L;
    return lua_getexdata(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaOpenresty_setExData(LuaState * _L, void * exdata) {
    lua_State *L = _L->L;
    lua_setexdata(L, exdata);
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_LuaOpenresty_getExData2(LuaState * _L) {
    lua_State *L = _L->L;
    return lua_getexdata2(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaOpenresty_setExData2(LuaState * _L, void * exdata) {
    lua_State *L = _L->L;
    lua_setexdata2(L, exdata);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaOpenresty_resetThread(LuaState * _L, LuaState * _th) {
    lua_State *L = _L->L;
    lua_State *th = _th->L;
    lua_resetthread(L, th);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni 21.0.0.8
// sha256:ced40124979c23c51233ac48d97ca35a2ba610640ffb1f5a6e1d63c310bd0b2d
