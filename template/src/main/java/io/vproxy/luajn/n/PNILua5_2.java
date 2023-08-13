package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@SuppressWarnings("unused")
@Function
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
    @Trivial
    @Critical
    void copy(PNILuaState _L, int fromidx, int toidx);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_upvalueid(L, funcindex, n);
            """
    )
    @Trivial
    @Critical
    MemorySegment upValueId(PNILuaState _L, int funcindex, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            lua_upvaluejoin(L, funcindex1, n1, funcindex2, n2);
            """
    )
    @Trivial
    @Critical
    void upValueJoin(PNILuaState _L, int funcindex1, int n1, int funcindex2, int n2);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_tonumberx(L, index, isnum);
            """
    )
    @Trivial
    @Critical
    double toNumberX(PNILuaState _L, int index, @Raw int[] isnum);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_tointegerx(L, index, isnum);
            """
    )
    @Trivial
    @Critical
    long toIntegerX(PNILuaState _L, int index, @Raw int[] isnum);
}
