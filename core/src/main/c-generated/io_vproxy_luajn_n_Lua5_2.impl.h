#include "io_vproxy_luajn_n_Lua5_2.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_Lua5_2_copy(LuaState * _L, int32_t fromidx, int32_t toidx) {
    lua_State *L = _L->L;
    lua_copy(L, fromidx, toidx);
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_Lua5_2_upValueId(LuaState * _L, int32_t funcindex, int32_t n) {
    lua_State *L = _L->L;
    return lua_upvalueid(L, funcindex, n);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_Lua5_2_upValueJoin(LuaState * _L, int32_t funcindex1, int32_t n1, int32_t funcindex2, int32_t n2) {
    lua_State *L = _L->L;
    lua_upvaluejoin(L, funcindex1, n1, funcindex2, n2);
}

JNIEXPORT double JNICALL JavaCritical_io_vproxy_luajn_n_Lua5_2_toNumberX(LuaState * _L, int32_t index, int32_t * isnum) {
    lua_State *L = _L->L;
    return lua_tonumberx(L, index, isnum);
}

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_luajn_n_Lua5_2_toIntegerX(LuaState * _L, int32_t index, int32_t * isnum) {
    lua_State *L = _L->L;
    return lua_tointegerx(L, index, isnum);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni 21.0.0.8
// sha256:c35dd17cc963dddabda3dbb88d7cf2006a558b654459888fed1f6a4209333568
