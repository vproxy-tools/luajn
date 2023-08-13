package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.Critical;
import io.vproxy.pni.annotation.Function;
import io.vproxy.pni.annotation.Impl;
import io.vproxy.pni.annotation.Trivial;

@SuppressWarnings("unused")
@Function
public interface PNILuaLib {
    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaL_openlibs(L);
            JavaCritical_io_vproxy_luajn_n_LuaLib_openLuaJN(_L);
            """
    )
    @Trivial
    @Critical
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
    @Trivial
    @Critical
    void openLuaJN(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_base(L);
            """
    )
    @Trivial
    @Critical
    void openBase(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_math(L);
            """
    )
    @Trivial
    @Critical
    void openMath(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_string(L);
            """
    )
    @Trivial
    @Critical
    void openString(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_table(L);
            """
    )
    @Trivial
    @Critical
    void openTable(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_io(L);
            """
    )
    @Trivial
    @Critical
    void openIO(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_os(L);
            """
    )
    @Trivial
    @Critical
    void openOS(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_package(L);
            """
    )
    @Trivial
    @Critical
    void openPackage(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            luaopen_debug(L);
            """
    )
    @Trivial
    @Critical
    void openDebug(PNILuaState _L);
}
