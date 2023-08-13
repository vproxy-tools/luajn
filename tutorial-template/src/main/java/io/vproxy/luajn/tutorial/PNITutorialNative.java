package io.vproxy.luajn.tutorial;

import io.vproxy.pni.annotation.*;

@Include({
    "<lua.h>",
    "<lauxlib.h>",
    "<lualib.h>",
})
@Struct
abstract class PNITutorialNative {
    int intValue;
    long longValue;

    @Impl(c = "")
    @Trivial
    @Critical
    abstract void dummyPlaceHolderForGeneratingImplHFile();
}
