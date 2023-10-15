package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@SuppressWarnings("unused")
@Downcall
@Include({
    "<luajit.h>",
})
public interface PNILuaOpenresty {
    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_getexdata(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    MemorySegment getExData(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            lua_setexdata(L, exdata);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void setExData(PNILuaState _L, MemorySegment exdata);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            return lua_getexdata2(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    MemorySegment getExData2(PNILuaState _L);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            lua_setexdata2(L, exdata);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void setExData2(PNILuaState _L, MemorySegment exdata);

    @Impl(
        // language="c"
        c = """
            lua_State *L = _L->L;
            lua_State *th = _th->L;
            lua_resetthread(L, th);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void resetThread(PNILuaState _L, PNILuaState _th);
}
