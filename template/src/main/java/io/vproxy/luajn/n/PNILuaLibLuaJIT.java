package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

@Downcall
public interface PNILuaLibLuaJIT {
    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_bit(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openBit(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_jit(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openJIT(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_ffi(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openFFI(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_string_buffer(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openStringBuffer(PNILuaState _L);
}
