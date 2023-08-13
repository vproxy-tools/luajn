package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

@SuppressWarnings("unused")
@Function
@Include({
    "<luajit.h>",
})
public interface PNILuaJIT {
    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return luaJIT_setmode(L, idx, mode);
            """
    )
    @Trivial
    @Critical
    int setMode(PNILuaState _L, int idx, int mode);
}
