package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

@SuppressWarnings("unused")
@Include({
    "<lua.h>",
})
@Downcall
public interface PNIHelper5_2 {
    @Impl(c = "return LUA_GCISRUNNING;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_GCISRUNNING();

    @Impl(c = "return LUA_OK;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_OK();

    @Impl(c = """
        #if LUA_VERSION_NUM >= 502
        return LUA_RIDX_GLOBALS;
        #else
        return 2;
        #endif
        """)
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_RIDX_GLOBALS();
}
