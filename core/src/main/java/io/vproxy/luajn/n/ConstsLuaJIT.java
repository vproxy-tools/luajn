package io.vproxy.luajn.n;

public class ConstsLuaJIT {
    private ConstsLuaJIT() {
    }

    public static final int LUAJIT_MODE_MASK = HelperLuaJIT.get().LUAJIT_MODE_MASK();
    public static final int LUAJIT_MODE_ENGINE = HelperLuaJIT.get().LUAJIT_MODE_ENGINE();
    public static final int LUAJIT_MODE_DEBUG = HelperLuaJIT.get().LUAJIT_MODE_DEBUG();
    public static final int LUAJIT_MODE_FUNC = HelperLuaJIT.get().LUAJIT_MODE_FUNC();
    public static final int LUAJIT_MODE_ALLFUNC = HelperLuaJIT.get().LUAJIT_MODE_ALLFUNC();
    public static final int LUAJIT_MODE_ALLSUBFUNC = HelperLuaJIT.get().LUAJIT_MODE_ALLSUBFUNC();
    public static final int LUAJIT_MODE_TRACE = HelperLuaJIT.get().LUAJIT_MODE_TRACE();
    public static final int LUAJIT_MODE_WRAPCFUNC = HelperLuaJIT.get().LUAJIT_MODE_WRAPCFUNC();
    public static final int LUAJIT_MODE_MAX = HelperLuaJIT.get().LUAJIT_MODE_MAX();
    public static final int LUAJIT_MODE_OFF = HelperLuaJIT.get().LUAJIT_MODE_OFF();
    public static final int LUAJIT_MODE_ON = HelperLuaJIT.get().LUAJIT_MODE_ON();
    public static final int LUAJIT_MODE_FLUSH = HelperLuaJIT.get().LUAJIT_MODE_FLUSH();
}
