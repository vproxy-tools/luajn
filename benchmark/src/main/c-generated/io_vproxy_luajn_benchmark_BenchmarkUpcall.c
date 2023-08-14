#include "io_vproxy_luajn_benchmark_BenchmarkUpcall.h"
#include <stdio.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

static int32_t (*_oneIntArgIntReturn)(int32_t);
static int32_t (*_twoIntArgIntReturn)(int32_t,int32_t);
static int32_t (*_oneRefArgIntReturn)(PNIRef *);

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_INIT(
    int32_t (*oneIntArgIntReturn)(int32_t),
    int32_t (*twoIntArgIntReturn)(int32_t,int32_t),
    int32_t (*oneRefArgIntReturn)(PNIRef *)
) {
    _oneIntArgIntReturn = oneIntArgIntReturn;
    _twoIntArgIntReturn = twoIntArgIntReturn;
    _oneRefArgIntReturn = oneRefArgIntReturn;
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_oneIntArgIntReturn(int32_t a) {
    if (_oneIntArgIntReturn == NULL) {
        printf("JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_oneIntArgIntReturn function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _oneIntArgIntReturn(a);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_twoIntArgIntReturn(int32_t a, int32_t b) {
    if (_twoIntArgIntReturn == NULL) {
        printf("JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_twoIntArgIntReturn function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _twoIntArgIntReturn(a, b);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_oneRefArgIntReturn(PNIRef * ref) {
    if (_oneRefArgIntReturn == NULL) {
        printf("JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_oneRefArgIntReturn function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _oneRefArgIntReturn(ref);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni 21.0.0.8
// sha256:b5437f383e76aae50feca43ab604437651e304896c59c9f7c29e9c515a8439a8
