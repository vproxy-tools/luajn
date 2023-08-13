package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@SuppressWarnings("unused")
@Include({
    "<lua.h>",
    "<lauxlib.h>",
    "<lualib.h>",
})
@Function
public interface PNIHelper {
    @Impl(
        include = "<stdio.h>",
        // language="c"
        c = """
            fflush(stdout);
            """
    )
    @Trivial
    @Critical
    void flushStdout();

    @Impl(
        include = "<string.h>",
        // language="c"
        c = """
            memset(b, c, len);
            """
    )
    @Trivial
    @Critical
    void memset(MemorySegment b, int c, long len);

    @Impl(
        // language="c"
        c = """
            static int K;
            return (void*)&K;
            """
    )
    @Trivial
    @Critical
    MemorySegment temporaryVariablesTableIndex();

    @Impl(c = "return LUA_REGISTRYINDEX;")
    @Trivial
    @Critical
    int LUA_REGISTRYINDEX();

    @Impl(c = """
        #if LUA_VERSION_NUM >= 502
        return -10001;
        #else
        return LUA_ENVIRONINDEX;
        #endif
        """)
    @Trivial
    @Critical
    int LUA_ENVIRONINDEX();

    @Impl(c = """
        #if LUA_VERSION_NUM >= 502
        return -10002;
        #else
        return LUA_GLOBALSINDEX;
        #endif
        """)
    @Trivial
    @Critical
    int LUA_GLOBALSINDEX();

    @Impl(c = "return LUA_YIELD;")
    @Trivial
    @Critical
    int LUA_YIELD();

    @Impl(c = "return LUA_ERRRUN;")
    @Trivial
    @Critical
    int LUA_ERRRUN();

    @Impl(c = "return LUA_ERRSYNTAX;")
    @Trivial
    @Critical
    int LUA_ERRSYNTAX();

    @Impl(c = "return LUA_ERRMEM;")
    @Trivial
    @Critical
    int LUA_ERRMEM();

    @Impl(c = "return LUA_ERRERR;")
    @Trivial
    @Critical
    int LUA_ERRERR();

    @Impl(c = "return LUA_MULTRET;")
    @Trivial
    @Critical
    int LUA_MULTRET();

    @Impl(c = "return LUA_TNONE;")
    @Trivial
    @Critical
    int LUA_TNONE();

    @Impl(c = "return LUA_TNIL;")
    @Trivial
    @Critical
    int LUA_TNIL();

    @Impl(c = "return LUA_TBOOLEAN;")
    @Trivial
    @Critical
    int LUA_TBOOLEAN();

    @Impl(c = "return LUA_TLIGHTUSERDATA;")
    @Trivial
    @Critical
    int LUA_TLIGHTUSERDATA();

    @Impl(c = "return LUA_TNUMBER;")
    @Trivial
    @Critical
    int LUA_TNUMBER();

    @Impl(c = "return LUA_TSTRING;")
    @Trivial
    @Critical
    int LUA_TSTRING();

    @Impl(c = "return LUA_TTABLE;")
    @Trivial
    @Critical
    int LUA_TTABLE();

    @Impl(c = "return LUA_TFUNCTION;")
    @Trivial
    @Critical
    int LUA_TFUNCTION();

    @Impl(c = "return LUA_TUSERDATA;")
    @Trivial
    @Critical
    int LUA_TUSERDATA();

    @Impl(c = "return LUA_TTHREAD;")
    @Trivial
    @Critical
    int LUA_TTHREAD();

    @Impl(c = "return LUA_MINSTACK;")
    @Trivial
    @Critical
    int LUA_MINSTACK();

    @Impl(c = "return LUA_GCSTOP;")
    @Trivial
    @Critical
    int LUA_GCSTOP();

    @Impl(c = "return LUA_GCRESTART;")
    @Trivial
    @Critical
    int LUA_GCRESTART();

    @Impl(c = "return LUA_GCCOLLECT;")
    @Trivial
    @Critical
    int LUA_GCCOLLECT();

    @Impl(c = "return LUA_GCCOUNT;")
    @Trivial
    @Critical
    int LUA_GCCOUNT();

    @Impl(c = "return LUA_GCCOUNTB;")
    @Trivial
    @Critical
    int LUA_GCCOUNTB();

    @Impl(c = "return LUA_GCSTEP;")
    @Trivial
    @Critical
    int LUA_GCSTEP();

    @Impl(c = "return LUA_GCSETPAUSE;")
    @Trivial
    @Critical
    int LUA_GCSETPAUSE();

    @Impl(c = "return LUA_GCSETSTEPMUL;")
    @Trivial
    @Critical
    int LUA_GCSETSTEPMUL();

    @Impl(c = "return LUA_HOOKCALL;")
    @Trivial
    @Critical
    int LUA_HOOKCALL();

    @Impl(c = "return LUA_HOOKRET;")
    @Trivial
    @Critical
    int LUA_HOOKRET();

    @Impl(c = "return LUA_HOOKLINE;")
    @Trivial
    @Critical
    int LUA_HOOKLINE();

    @Impl(c = "return LUA_HOOKCOUNT;")
    @Trivial
    @Critical
    int LUA_HOOKCOUNT();

    @Impl(c = """
        #if LUA_VERSION_NUM >= 502
        return 4;
        #else
        return LUA_HOOKTAILRET;
        #endif
        """)
    @Trivial
    @Critical
    int LUA_HOOKTAILRET();

    @Impl(c = "return LUA_MASKCALL;")
    @Trivial
    @Critical
    int LUA_MASKCALL();

    @Impl(c = "return LUA_MASKRET;")
    @Trivial
    @Critical
    int LUA_MASKRET();

    @Impl(c = "return LUA_MASKLINE;")
    @Trivial
    @Critical
    int LUA_MASKLINE();

    @Impl(c = "return LUA_MASKCOUNT;")
    @Trivial
    @Critical
    int LUA_MASKCOUNT();
}
