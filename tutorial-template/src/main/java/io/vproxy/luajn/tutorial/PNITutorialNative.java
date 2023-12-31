package io.vproxy.luajn.tutorial;

import io.vproxy.pni.annotation.*;

@Include({
    "<lua.h>",
    "<lauxlib.h>",
    "<lualib.h>",
})
@Struct
@AlwaysAligned
abstract class PNITutorialNative {
    int intValue;
    long longValue;

    @Impl(c = "")
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void dummyPlaceHolderForGeneratingImplHFile();
}
