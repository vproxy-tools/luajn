package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@SuppressWarnings("unused")
@Include({
    "<lua.h>",
    "<lauxlib.h>",
    "<lualib.h>",
})
@Downcall
public interface PNIHelper {
    @Impl(
        include = "<stdio.h>",
        // language="c"
        c = """
            fflush(stdout);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void flushStdout();

    @Impl(
        include = "<string.h>",
        // language="c"
        c = """
            memset(b, c, len);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    void memset(MemorySegment b, int c, long len);

    @Impl(
        // language="c"
        c = """
            static int K;
            return (void*)&K;
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    MemorySegment temporaryVariablesTableIndex();

    @Impl(c = "return LUA_REGISTRYINDEX;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_REGISTRYINDEX();

    @Impl(c = """
        #if LUA_VERSION_NUM >= 502
        return -10001;
        #else
        return LUA_ENVIRONINDEX;
        #endif
        """)
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_ENVIRONINDEX();

    @Impl(c = """
        #if LUA_VERSION_NUM >= 502
        return -10002;
        #else
        return LUA_GLOBALSINDEX;
        #endif
        """)
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_GLOBALSINDEX();

    @Impl(c = "return LUA_YIELD;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_YIELD();

    @Impl(c = "return LUA_ERRRUN;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_ERRRUN();

    @Impl(c = "return LUA_ERRSYNTAX;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_ERRSYNTAX();

    @Impl(c = "return LUA_ERRMEM;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_ERRMEM();

    @Impl(c = "return LUA_ERRERR;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_ERRERR();

    @Impl(c = "return LUA_MULTRET;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_MULTRET();

    @Impl(c = "return LUA_TNONE;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_TNONE();

    @Impl(c = "return LUA_TNIL;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_TNIL();

    @Impl(c = "return LUA_TBOOLEAN;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_TBOOLEAN();

    @Impl(c = "return LUA_TLIGHTUSERDATA;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_TLIGHTUSERDATA();

    @Impl(c = "return LUA_TNUMBER;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_TNUMBER();

    @Impl(c = "return LUA_TSTRING;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_TSTRING();

    @Impl(c = "return LUA_TTABLE;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_TTABLE();

    @Impl(c = "return LUA_TFUNCTION;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_TFUNCTION();

    @Impl(c = "return LUA_TUSERDATA;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_TUSERDATA();

    @Impl(c = "return LUA_TTHREAD;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_TTHREAD();

    @Impl(c = "return LUA_MINSTACK;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_MINSTACK();

    @Impl(c = "return LUA_GCSTOP;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_GCSTOP();

    @Impl(c = "return LUA_GCRESTART;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_GCRESTART();

    @Impl(c = "return LUA_GCCOLLECT;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_GCCOLLECT();

    @Impl(c = "return LUA_GCCOUNT;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_GCCOUNT();

    @Impl(c = "return LUA_GCCOUNTB;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_GCCOUNTB();

    @Impl(c = "return LUA_GCSTEP;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_GCSTEP();

    @Impl(c = "return LUA_GCSETPAUSE;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_GCSETPAUSE();

    @Impl(c = "return LUA_GCSETSTEPMUL;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_GCSETSTEPMUL();

    @Impl(c = "return LUA_HOOKCALL;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_HOOKCALL();

    @Impl(c = "return LUA_HOOKRET;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_HOOKRET();

    @Impl(c = "return LUA_HOOKLINE;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_HOOKLINE();

    @Impl(c = "return LUA_HOOKCOUNT;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_HOOKCOUNT();

    @Impl(c = """
        #if LUA_VERSION_NUM >= 502
        return 4;
        #else
        return LUA_HOOKTAILRET;
        #endif
        """)
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_HOOKTAILRET();

    @Impl(c = "return LUA_MASKCALL;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_MASKCALL();

    @Impl(c = "return LUA_MASKRET;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_MASKRET();

    @Impl(c = "return LUA_MASKLINE;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_MASKLINE();

    @Impl(c = "return LUA_MASKCOUNT;")
    @LinkerOption.Critical
    @Style(Styles.critical)
    int LUA_MASKCOUNT();
}
