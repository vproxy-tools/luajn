package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

@SuppressWarnings("unused")
@Function
@Include({
    "<lua.h>",
    "<lauxlib.h>",
    "<lualib.h>",
})
public interface PNILua5_3 {
    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            lua_isyieldable(L);
            """
    )
    @Trivial
    @Critical
    void isyieldable(PNILuaState _L);
}
