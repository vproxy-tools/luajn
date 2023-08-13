#include "io_vproxy_luajn_n_Lua5_3.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_Lua5_3_isyieldable(LuaState * _L) {
    lua_State *L = _L->L;
    lua_isyieldable(L);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni 21.0.0.8
// sha256:ba38190ff6e8019191fa79acd51631fd9326ac9b047d3d4f4bbb18e7226d0e93
