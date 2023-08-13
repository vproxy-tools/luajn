#include "io_vproxy_luajn_n_LuaLibLuaJIT.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLibLuaJIT_openBit(LuaState * _L) {
    lua_State *L = _L->L;
    luaopen_bit(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLibLuaJIT_openJIT(LuaState * _L) {
    lua_State *L = _L->L;
    luaopen_jit(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLibLuaJIT_openFFI(LuaState * _L) {
    lua_State *L = _L->L;
    luaopen_ffi(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaLibLuaJIT_openStringBuffer(LuaState * _L) {
    lua_State *L = _L->L;
    luaopen_string_buffer(L);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni 21.0.0.8
// sha256:36e4ec50d7f7fa60855f3198a8a160ca844813aefc68dcdbe1e7ca33620b5cd9
