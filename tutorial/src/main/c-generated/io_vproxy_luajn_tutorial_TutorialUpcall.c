#include "io_vproxy_luajn_tutorial_TutorialUpcall.h"
#include <stdio.h>
#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif

static void (*_add)(PNIRef *,char *);
static void (*_remove)(PNIRef *,int32_t);
static int32_t (*_size)(PNIRef *);

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_tutorial_TutorialUpcall_INIT(
    void (*add)(PNIRef *,char *),
    void (*remove)(PNIRef *,int32_t),
    int32_t (*size)(PNIRef *)
) {
    _add = add;
    _remove = remove;
    _size = size;
}

JNIEXPORT void JNICALL luajn_tutorial_ls_add(PNIRef * ls, char * str) {
    if (_add == NULL) {
        printf("luajn_tutorial_ls_add function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _add(ls, str);
}

JNIEXPORT void JNICALL luajn_tutorial_ls_remove(PNIRef * ls, int32_t index) {
    if (_remove == NULL) {
        printf("luajn_tutorial_ls_remove function pointer is null");
        fflush(stdout);
        exit(1);
    }
    _remove(ls, index);
}

JNIEXPORT int32_t JNICALL luajn_tutorial_ls_size(PNIRef * ls) {
    if (_size == NULL) {
        printf("luajn_tutorial_ls_size function pointer is null");
        fflush(stdout);
        exit(1);
    }
    return _size(ls);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni 21.0.0.8
// sha256:7c65cd22e016fa54feea894e9e37b2b845614463991f4e737943c6286655bfd4
