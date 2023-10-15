package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@Downcall
public interface PNILuaDebugFuncs {
    @Impl(
        // language="c"
        c = """
            return sizeof(struct lua_Debug);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    long sizeofLuaDebug();

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_gethook(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    MemorySegment getHook(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_gethookcount(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    int getHookCount(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_gethookmask(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    int getHookMask(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_getinfo(L, what, ar);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    int getInfo(PNILuaState _L, String what, PNILuaDebug ar);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return (char*) lua_getlocal(L, ar, n);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    String getLocal(PNILuaState _L, PNILuaDebug ar, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_getstack(L, level, ar);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    int getStack(PNILuaState _L, int level, PNILuaDebug ar);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return (char*) lua_getupvalue(L, funcIndex, n);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
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
    @LinkerOption.Critical
    @Style(Styles.critical)
    int setHook(PNILuaState _L, MemorySegment hookF, int mask, int count);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return (char*) lua_setlocal(L, ar, n);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    String setLocal(PNILuaState _L, PNILuaDebug ar, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return (char*) lua_setupvalue(L, funcIndex, n);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    String setUpValue(PNILuaState _L, int funcIndex, int n);
}
