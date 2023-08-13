package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

@SuppressWarnings("unused")
@Include({
    "<luajit.h>",
})
@Function
public interface PNIHelperLuaJIT {
    @Impl(c = "return LUAJIT_MODE_MASK;")
    @Trivial
    @Critical
    int LUAJIT_MODE_MASK();

    @Impl(c = "return LUAJIT_MODE_ENGINE;")
    @Trivial
    @Critical
    int LUAJIT_MODE_ENGINE();

    @Impl(c = "return LUAJIT_MODE_DEBUG;")
    @Trivial
    @Critical
    int LUAJIT_MODE_DEBUG();

    @Impl(c = "return LUAJIT_MODE_FUNC;")
    @Trivial
    @Critical
    int LUAJIT_MODE_FUNC();

    @Impl(c = "return LUAJIT_MODE_ALLFUNC;")
    @Trivial
    @Critical
    int LUAJIT_MODE_ALLFUNC();

    @Impl(c = "return LUAJIT_MODE_ALLSUBFUNC;")
    @Trivial
    @Critical
    int LUAJIT_MODE_ALLSUBFUNC();

    @Impl(c = "return LUAJIT_MODE_TRACE;")
    @Trivial
    @Critical
    int LUAJIT_MODE_TRACE();

    @Impl(c = "return LUAJIT_MODE_WRAPCFUNC;")
    @Trivial
    @Critical
    int LUAJIT_MODE_WRAPCFUNC();

    @Impl(c = "return LUAJIT_MODE_MAX;")
    @Trivial
    @Critical
    int LUAJIT_MODE_MAX();

    @Impl(c = "return LUAJIT_MODE_OFF;")
    @Trivial
    @Critical
    int LUAJIT_MODE_OFF();

    @Impl(c = "return LUAJIT_MODE_ON;")
    @Trivial
    @Critical
    int LUAJIT_MODE_ON();

    @Impl(c = "return LUAJIT_MODE_FLUSH;")
    @Trivial
    @Critical
    int LUAJIT_MODE_FLUSH();
}
