/* DO NOT EDIT THIS FILE - it is machine generated */
/* Header for class io_vproxy_luajn_n_LuaDebugFuncs */
#ifndef _Included_io_vproxy_luajn_n_LuaDebugFuncs
#define _Included_io_vproxy_luajn_n_LuaDebugFuncs
#ifdef __cplusplus
extern "C" {
#endif

#ifdef __cplusplus
}
#endif

#include <jni.h>
#include <pni.h>
#include "io_vproxy_luajn_n_LuaState.h"
#include "io_vproxy_luajn_n_LuaDebug.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_sizeofLuaDebug(void);
JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getHook(LuaState * _L);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getHookCount(LuaState * _L);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getHookMask(LuaState * _L);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getInfo(LuaState * _L, char * what, lua_Debug * ar);
JNIEXPORT char * JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getLocal(LuaState * _L, lua_Debug * ar, int32_t n);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getStack(LuaState * _L, int32_t level, lua_Debug * ar);
JNIEXPORT char * JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_getUpValue(LuaState * _L, int32_t funcIndex, int32_t n);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_setHook(LuaState * _L, void * hookF, int32_t mask, int32_t count);
JNIEXPORT char * JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_setLocal(LuaState * _L, lua_Debug * ar, int32_t n);
JNIEXPORT char * JNICALL JavaCritical_io_vproxy_luajn_n_LuaDebugFuncs_setUpValue(LuaState * _L, int32_t funcIndex, int32_t n);

#ifdef __cplusplus
}
#endif
#endif // _Included_io_vproxy_luajn_n_LuaDebugFuncs
// metadata.generator-version: pni 21.0.0.8
// sha256:1fa226b33145cbcc2dd60b1bd63eca8f6c93d32417537f2fc3d48b6105691968
