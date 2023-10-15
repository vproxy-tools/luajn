package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

@SuppressWarnings("unused")
@Downcall
public interface PNILuaLib {
    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaL_openlibs(L);
            JavaCritical_io_vproxy_luajn_n_LuaLib_openLuaJN(_L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openLibs(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luajn_register_module(L, "luajn");
            lua_pushcfunction(L, luajn_PNIFuncInvoke);
            lua_setfield(L, -2, "upcall");
            lua_pushcfunction(L, luajn_PNIFuncRelease);
            lua_setfield(L, -2, "release_func");
            lua_pushcfunction(L, luajn_PNIRefRelease);
            lua_setfield(L, -2, "release_ref");
            lua_pop(L, 1);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openLuaJN(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_base(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openBase(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_math(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openMath(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_string(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openString(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_table(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openTable(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_io(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openIO(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_os(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openOS(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_package(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openPackage(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_debug(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void openDebug(PNILuaState _L);
}
