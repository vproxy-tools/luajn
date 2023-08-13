package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.Critical;
import io.vproxy.pni.annotation.Function;
import io.vproxy.pni.annotation.Impl;
import io.vproxy.pni.annotation.Trivial;

@Function
public interface PNILuaLibLuaJIT {
    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_bit(L);
            """
    )
    @Trivial
    @Critical
    void openBit(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_jit(L);
            """
    )
    @Trivial
    @Critical
    void openJIT(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_ffi(L);
            """
    )
    @Trivial
    @Critical
    void openFFI(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_string_buffer(L);
            """
    )
    @Trivial
    @Critical
    void openStringBuffer(PNILuaState _L);
}
