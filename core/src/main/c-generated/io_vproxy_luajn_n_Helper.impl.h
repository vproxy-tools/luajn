#include "io_vproxy_luajn_n_Helper.h"
#include <stdio.h>
#include <string.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_Helper_flushStdout(void) {
    fflush(stdout);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_Helper_memset(void * b, int32_t c, int64_t len) {
    memset(b, c, len);
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_Helper_temporaryVariablesTableIndex(void) {
    static int K;
    return (void*)&K;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_REGISTRYINDEX(void) {
    return LUA_REGISTRYINDEX;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_ENVIRONINDEX(void) {
    #if LUA_VERSION_NUM >= 502
    return -10001;
    #else
    return LUA_ENVIRONINDEX;
    #endif
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_GLOBALSINDEX(void) {
    #if LUA_VERSION_NUM >= 502
    return -10002;
    #else
    return LUA_GLOBALSINDEX;
    #endif
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_YIELD(void) {
    return LUA_YIELD;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_ERRRUN(void) {
    return LUA_ERRRUN;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_ERRSYNTAX(void) {
    return LUA_ERRSYNTAX;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_ERRMEM(void) {
    return LUA_ERRMEM;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_ERRERR(void) {
    return LUA_ERRERR;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_MULTRET(void) {
    return LUA_MULTRET;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_TNONE(void) {
    return LUA_TNONE;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_TNIL(void) {
    return LUA_TNIL;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_TBOOLEAN(void) {
    return LUA_TBOOLEAN;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_TLIGHTUSERDATA(void) {
    return LUA_TLIGHTUSERDATA;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_TNUMBER(void) {
    return LUA_TNUMBER;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_TSTRING(void) {
    return LUA_TSTRING;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_TTABLE(void) {
    return LUA_TTABLE;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_TFUNCTION(void) {
    return LUA_TFUNCTION;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_TUSERDATA(void) {
    return LUA_TUSERDATA;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_TTHREAD(void) {
    return LUA_TTHREAD;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_MINSTACK(void) {
    return LUA_MINSTACK;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCSTOP(void) {
    return LUA_GCSTOP;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCRESTART(void) {
    return LUA_GCRESTART;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCCOLLECT(void) {
    return LUA_GCCOLLECT;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCCOUNT(void) {
    return LUA_GCCOUNT;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCCOUNTB(void) {
    return LUA_GCCOUNTB;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCSTEP(void) {
    return LUA_GCSTEP;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCSETPAUSE(void) {
    return LUA_GCSETPAUSE;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCSETSTEPMUL(void) {
    return LUA_GCSETSTEPMUL;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_HOOKCALL(void) {
    return LUA_HOOKCALL;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_HOOKRET(void) {
    return LUA_HOOKRET;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_HOOKLINE(void) {
    return LUA_HOOKLINE;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_HOOKCOUNT(void) {
    return LUA_HOOKCOUNT;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_HOOKTAILRET(void) {
    #if LUA_VERSION_NUM >= 502
    return 4;
    #else
    return LUA_HOOKTAILRET;
    #endif
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_MASKCALL(void) {
    return LUA_MASKCALL;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_MASKRET(void) {
    return LUA_MASKRET;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_MASKLINE(void) {
    return LUA_MASKLINE;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_Helper_LUA_MASKCOUNT(void) {
    return LUA_MASKCOUNT;
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni 21.0.0.8
// sha256:ada79ada79c7b64002f0ed1cf9dfb38f52aa24df866ba5f9dde32513cf001701
