package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.Critical;
import io.vproxy.pni.annotation.Function;
import io.vproxy.pni.annotation.Impl;
import io.vproxy.pni.annotation.Trivial;

import java.lang.foreign.MemorySegment;

@Function
public interface PNILuaDebugFuncs {
    @Impl(
        // language="c"
        c = """
            return sizeof(struct lua_Debug);
            """
    )
    @Trivial
    @Critical
    long sizeofLuaDebug();

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_gethook(L);
            """
    )
    @Trivial
    @Critical
    MemorySegment getHook(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_gethookcount(L);
            """
    )
    @Trivial
    @Critical
    int getHookCount(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_gethookmask(L);
            """
    )
    @Trivial
    @Critical
    int getHookMask(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_getinfo(L, what, ar);
            """
    )
    @Trivial
    @Critical
    int getInfo(PNILuaState _L, String what, PNILuaDebug ar);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return (char*) lua_getlocal(L, ar, n);
            """
    )
    @Trivial
    @Critical
    String getLocal(PNILuaState _L, PNILuaDebug ar, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_getstack(L, level, ar);
            """
    )
    @Trivial
    @Critical
    int getStack(PNILuaState _L, int level, PNILuaDebug ar);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return (char*) lua_getupvalue(L, funcIndex, n);
            """
    )
    @Trivial
    @Critical
    String getUpValue(PNILuaState _L, int funcIndex, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            #if LUA_VERSION_NUM >= 503
            lua_sethook(L, (lua_Hook) hookF, mask, count);
            return 1;
            #else
            return lua_sethook(L, (lua_Hook) hookF, mask, count);
            #endif
            """
    )
    @Trivial
    @Critical
    int setHook(PNILuaState _L, MemorySegment hookF, int mask, int count);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return (char*) lua_setlocal(L, ar, n);
            """
    )
    @Trivial
    @Critical
    String setLocal(PNILuaState _L, PNILuaDebug ar, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return (char*) lua_setupvalue(L, funcIndex, n);
            """
    )
    @Trivial
    @Critical
    String setUpValue(PNILuaState _L, int funcIndex, int n);
}
