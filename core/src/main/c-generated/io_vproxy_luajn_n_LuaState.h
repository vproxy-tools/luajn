/* DO NOT EDIT THIS FILE - it is machine generated */
/* Header for class io_vproxy_luajn_n_LuaState */
#ifndef _Included_io_vproxy_luajn_n_LuaState
#define _Included_io_vproxy_luajn_n_LuaState
#ifdef __cplusplus
extern "C" {
#endif

struct LuaState;
typedef struct LuaState LuaState;

#ifdef __cplusplus
}
#endif

#include <jni.h>
#include <pni.h>
#include <lua.h>
#include <lauxlib.h>
#include <lualib.h>
#include "io_vproxy_luajn_n_LuaState.h"

#ifdef __cplusplus
extern "C" {
#endif

PNIEnvExpand(LuaState, LuaState *)
PNIBufExpand(LuaState, LuaState, 8)

struct LuaState {
    void * L;
};

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_close(LuaState * self);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_call(LuaState * self, int32_t nargs, int32_t nresults);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_callTrivial(LuaState * self, int32_t nargs, int32_t nresults);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_checkStack(LuaState * self, int32_t extra);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_concat(LuaState * self, int32_t n);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_createTable(LuaState * self, int32_t narr, int32_t nrec);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_equal(LuaState * self, int32_t index1, int32_t index2);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_error(LuaState * self);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_getField(LuaState * self, int32_t index, char * k);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_getFEnv(LuaState * self, int32_t index);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_getGlobal(LuaState * self, char * name);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_getMetaTable(LuaState * self, int32_t index);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_getTable(LuaState * self, int32_t index);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_getTop(LuaState * self);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_insert(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isBoolean(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isCFunction(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isFunction(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isLightUserData(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isNil(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isNone(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isNoneOrNil(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isNumber(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isString(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isTable(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isThread(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isUserdata(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_lessthan(LuaState * self, int32_t idx1, int32_t idx2);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_newTable(LuaState * self);
JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_newUserData(LuaState * self, int64_t size);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_next(LuaState * self, int32_t index);
JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_objLen(LuaState * self, int32_t index);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pcall(LuaState * self, int32_t nargs, int32_t nresults, int32_t errfunc);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pcallTrivial(LuaState * self, int32_t nargs, int32_t nresults, int32_t errfunc);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pop(LuaState * self, int32_t n);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushBoolean(LuaState * self, uint8_t b);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushClosure(LuaState * self, PNIFunc * fn, int32_t n);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushCClosure(LuaState * self, void * fn, int32_t n);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushCFunction(LuaState * self, void * fn);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushFunction(LuaState * self, PNIFunc * fn);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushInteger(LuaState * self, int64_t n);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushLightUserData(LuaState * self, void * p);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushLString(LuaState * self, char * s, int64_t len);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushNil(LuaState * self);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushNumber(LuaState * self, double n);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushString(LuaState * self, char * s);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushThread(LuaState * self);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushUserData(LuaState * self, void * data, int64_t size, char * metatableName);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushValue(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_rawEqual(LuaState * self, int32_t idx1, int32_t idx2);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_rawGet(LuaState * self, int32_t index);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_rawGetI(LuaState * self, int32_t index, int32_t n);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_rawSet(LuaState * self, int32_t index);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_rawSetI(LuaState * self, int32_t index, int32_t n);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_remove(LuaState * self, int32_t index);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_replace(LuaState * self, int32_t index);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_setFEnv(LuaState * self, int32_t index);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_setField(LuaState * self, int32_t index, char * k);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_setGlobal(LuaState * self, char * name);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_setMetaTable(LuaState * self, int32_t index);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_setTable(LuaState * self, int32_t index);
JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_setTop(LuaState * self, int32_t index);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_strLen(LuaState * self, int32_t index);
JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toBoolean(LuaState * self, int32_t index);
JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toCFunction(LuaState * self, int32_t index);
JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toInteger(LuaState * self, int32_t index);
JNIEXPORT char * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toLString(LuaState * self, int32_t index, uint64_t * len);
JNIEXPORT double JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toNumber(LuaState * self, int32_t index);
JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toPointer(LuaState * self, int32_t index);
JNIEXPORT char * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toString(LuaState * self, int32_t index);
JNIEXPORT LuaState * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toThread(LuaState * self, int32_t index, LuaState * return_);
JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toUserData(LuaState * self, int32_t index);
JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_type(LuaState * self, int32_t index);

#ifdef __cplusplus
}
#endif
#endif // _Included_io_vproxy_luajn_n_LuaState
// metadata.generator-version: pni 21.0.0.13
// sha256:69bf6ccf49bc466e0a74e0f211dba84cd54f3bc6681b2de1a9fb029301c229c3
