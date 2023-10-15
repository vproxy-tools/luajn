package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

@SuppressWarnings("unused")
@Include({
    "<luajit.h>",
})
@Downcall
public interface PNIHelperLuaJIT {
    @Impl(c = "return LUAJIT_MODE_MASK;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUAJIT_MODE_MASK();

    @Impl(c = "return LUAJIT_MODE_ENGINE;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUAJIT_MODE_ENGINE();

    @Impl(c = "return LUAJIT_MODE_DEBUG;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUAJIT_MODE_DEBUG();

    @Impl(c = "return LUAJIT_MODE_FUNC;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUAJIT_MODE_FUNC();

    @Impl(c = "return LUAJIT_MODE_ALLFUNC;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUAJIT_MODE_ALLFUNC();

    @Impl(c = "return LUAJIT_MODE_ALLSUBFUNC;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUAJIT_MODE_ALLSUBFUNC();

    @Impl(c = "return LUAJIT_MODE_TRACE;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUAJIT_MODE_TRACE();

    @Impl(c = "return LUAJIT_MODE_WRAPCFUNC;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUAJIT_MODE_WRAPCFUNC();

    @Impl(c = "return LUAJIT_MODE_MAX;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUAJIT_MODE_MAX();

    @Impl(c = "return LUAJIT_MODE_OFF;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUAJIT_MODE_OFF();

    @Impl(c = "return LUAJIT_MODE_ON;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUAJIT_MODE_ON();

    @Impl(c = "return LUAJIT_MODE_FLUSH;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUAJIT_MODE_FLUSH();
}
