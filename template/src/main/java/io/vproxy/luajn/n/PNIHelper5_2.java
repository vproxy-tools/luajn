package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

@SuppressWarnings("unused")
@Include({
    "<lua.h>",
})
@Function
public interface PNIHelper5_2 {
    @Impl(c = "return LUA_GCISRUNNING;")
    @Trivial
    @Critical
    int LUA_GCISRUNNING();

    @Impl(c = "return LUA_OK;")
    @Trivial
    @Critical
    int LUA_OK();

    @Impl(c = """
        #if LUA_VERSION_NUM >= 502
        return LUA_RIDX_GLOBALS;
        #else
        return 2;
        #endif
        """)
    @Trivial
    @Critical
    int LUA_RIDX_GLOBALS();
}
