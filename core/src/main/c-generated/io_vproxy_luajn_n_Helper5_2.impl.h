#include "io_vproxy_luajn_n_Helper5_2.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper5_2_LUA_GCISRUNNING(void) {
    return LUA_GCISRUNNING;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper5_2_LUA_OK(void) {
    return LUA_OK;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper5_2_LUA_RIDX_GLOBALS(void) {
    #if LUA_VERSION_NUM >= 502
    return LUA_RIDX_GLOBALS;
    #else
    return 2;
    #endif
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni 21.0.0.8
// sha256:2cdb2f0fce2508f6e9c55a1ee1052f4845ff46934860180f347f058ff16b39e2
