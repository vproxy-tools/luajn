package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@SuppressWarnings("unused")
@Downcall
@Include({
    "<lua.h>",
    "<lauxlib.h>",
    "<lualib.h>",
})
public interface PNILua5_2 {
    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            lua_copy(L, fromidx, toidx);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void copy(PNILuaState _L, int fromidx, int toidx);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_upvalueid(L, funcindex, n);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    MemorySegment upValueId(PNILuaState _L, int funcindex, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            lua_upvaluejoin(L, funcindex1, n1, funcindex2, n2);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void upValueJoin(PNILuaState _L, int funcindex1, int n1, int funcindex2, int n2);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_tonumberx(L, index, isnum);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    double toNumberX(PNILuaState _L, int index, @Raw int[] isnum);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_tointegerx(L, index, isnum);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    long toIntegerX(PNILuaState _L, int index, @Raw int[] isnum);
}
