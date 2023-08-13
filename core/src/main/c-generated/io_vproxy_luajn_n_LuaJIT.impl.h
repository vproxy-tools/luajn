#include "io_vproxy_luajn_n_LuaJIT.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaJIT_setMode(LuaState * _L, int32_t idx, int32_t mode) {
    lua_State *L = _L->L;
    return luaJIT_setmode(L, idx, mode);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni 21.0.0.8
// sha256:90c8b98fde9169221b3844d2e62b77495c4932731a800cae249d2bf415d3b856
