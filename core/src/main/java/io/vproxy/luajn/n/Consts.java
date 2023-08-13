package io.vproxy.luajn.n;

import java.lang.foreign.MemorySegment;

public class Consts {
    private Consts() {
    }

    public static final MemorySegment TEMPORARY_VARIABLES_TABLE_INDEX = Helper.get().temporaryVariablesTableIndex();

    public static final int LUA_REGISTRYINDEX = Helper.get().LUA_REGISTRYINDEX();
    public static final int LUA_ENVIRONINDEX = Helper.get().LUA_ENVIRONINDEX();
    public static final int LUA_GLOBALSINDEX = Helper.get().LUA_GLOBALSINDEX();

    public static final int LUA_YIELD = Helper.get().LUA_YIELD();
    public static final int LUA_ERRRUN = Helper.get().LUA_ERRRUN();
    public static final int LUA_ERRSYNTAX = Helper.get().LUA_ERRSYNTAX();
    public static final int LUA_ERRMEM = Helper.get().LUA_ERRMEM();
    public static final int LUA_ERRERR = Helper.get().LUA_ERRERR();

    public static final int LUA_MULTRET = Helper.get().LUA_MULTRET();

    public static final int LUA_TNONE = Helper.get().LUA_TNONE();

    public static final int LUA_TNIL = Helper.get().LUA_TNIL();
    public static final int LUA_TBOOLEAN = Helper.get().LUA_TBOOLEAN();
    public static final int LUA_TLIGHTUSERDATA = Helper.get().LUA_TLIGHTUSERDATA();
    public static final int LUA_TNUMBER = Helper.get().LUA_TNUMBER();
    public static final int LUA_TSTRING = Helper.get().LUA_TSTRING();
    public static final int LUA_TTABLE = Helper.get().LUA_TTABLE();
    public static final int LUA_TFUNCTION = Helper.get().LUA_TFUNCTION();
    public static final int LUA_TUSERDATA = Helper.get().LUA_TUSERDATA();
    public static final int LUA_TTHREAD = Helper.get().LUA_TTHREAD();

    public static final int LUA_MINSTACK = Helper.get().LUA_MINSTACK();

    public static final int LUA_GCSTOP = Helper.get().LUA_GCSTOP();
    public static final int LUA_GCRESTART = Helper.get().LUA_GCRESTART();
    public static final int LUA_GCCOLLECT = Helper.get().LUA_GCCOLLECT();
    public static final int LUA_GCCOUNT = Helper.get().LUA_GCCOUNT();
    public static final int LUA_GCCOUNTB = Helper.get().LUA_GCCOUNTB();
    public static final int LUA_GCSTEP = Helper.get().LUA_GCSTEP();
    public static final int LUA_GCSETPAUSE = Helper.get().LUA_GCSETPAUSE();
    public static final int LUA_GCSETSTEPMUL = Helper.get().LUA_GCSETSTEPMUL();

    public static final int LUA_HOOKCALL = Helper.get().LUA_HOOKCALL();
    public static final int LUA_HOOKRET = Helper.get().LUA_HOOKRET();
    public static final int LUA_HOOKLINE = Helper.get().LUA_HOOKLINE();
    public static final int LUA_HOOKCOUNT = Helper.get().LUA_HOOKCOUNT();
    public static final int LUA_HOOKTAILRET = Helper.get().LUA_HOOKTAILRET();
    public static final int LUA_MASKCALL = Helper.get().LUA_MASKCALL();
    public static final int LUA_MASKRET = Helper.get().LUA_MASKRET();
    public static final int LUA_MASKLINE = Helper.get().LUA_MASKLINE();
    public static final int LUA_MASKCOUNT = Helper.get().LUA_MASKCOUNT();
}
