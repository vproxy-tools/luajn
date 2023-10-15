package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class HelperLuaJIT {
    private HelperLuaJIT() {
    }

    private static final HelperLuaJIT INSTANCE = new HelperLuaJIT();

    public static HelperLuaJIT get() {
        return INSTANCE;
    }

    private static final MethodHandle LUAJIT_MODE_MASKMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_HelperLuaJIT_LUAJIT_MODE_MASK");

    public int LUAJIT_MODE_MASK() {
        int RESULT;
        try {
            RESULT = (int) LUAJIT_MODE_MASKMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUAJIT_MODE_ENGINEMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_HelperLuaJIT_LUAJIT_MODE_ENGINE");

    public int LUAJIT_MODE_ENGINE() {
        int RESULT;
        try {
            RESULT = (int) LUAJIT_MODE_ENGINEMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUAJIT_MODE_DEBUGMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_HelperLuaJIT_LUAJIT_MODE_DEBUG");

    public int LUAJIT_MODE_DEBUG() {
        int RESULT;
        try {
            RESULT = (int) LUAJIT_MODE_DEBUGMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUAJIT_MODE_FUNCMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_HelperLuaJIT_LUAJIT_MODE_FUNC");

    public int LUAJIT_MODE_FUNC() {
        int RESULT;
        try {
            RESULT = (int) LUAJIT_MODE_FUNCMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUAJIT_MODE_ALLFUNCMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_HelperLuaJIT_LUAJIT_MODE_ALLFUNC");

    public int LUAJIT_MODE_ALLFUNC() {
        int RESULT;
        try {
            RESULT = (int) LUAJIT_MODE_ALLFUNCMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUAJIT_MODE_ALLSUBFUNCMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_HelperLuaJIT_LUAJIT_MODE_ALLSUBFUNC");

    public int LUAJIT_MODE_ALLSUBFUNC() {
        int RESULT;
        try {
            RESULT = (int) LUAJIT_MODE_ALLSUBFUNCMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUAJIT_MODE_TRACEMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_HelperLuaJIT_LUAJIT_MODE_TRACE");

    public int LUAJIT_MODE_TRACE() {
        int RESULT;
        try {
            RESULT = (int) LUAJIT_MODE_TRACEMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUAJIT_MODE_WRAPCFUNCMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_HelperLuaJIT_LUAJIT_MODE_WRAPCFUNC");

    public int LUAJIT_MODE_WRAPCFUNC() {
        int RESULT;
        try {
            RESULT = (int) LUAJIT_MODE_WRAPCFUNCMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUAJIT_MODE_MAXMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_HelperLuaJIT_LUAJIT_MODE_MAX");

    public int LUAJIT_MODE_MAX() {
        int RESULT;
        try {
            RESULT = (int) LUAJIT_MODE_MAXMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUAJIT_MODE_OFFMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_HelperLuaJIT_LUAJIT_MODE_OFF");

    public int LUAJIT_MODE_OFF() {
        int RESULT;
        try {
            RESULT = (int) LUAJIT_MODE_OFFMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUAJIT_MODE_ONMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_HelperLuaJIT_LUAJIT_MODE_ON");

    public int LUAJIT_MODE_ON() {
        int RESULT;
        try {
            RESULT = (int) LUAJIT_MODE_ONMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUAJIT_MODE_FLUSHMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_HelperLuaJIT_LUAJIT_MODE_FLUSH");

    public int LUAJIT_MODE_FLUSH() {
        int RESULT;
        try {
            RESULT = (int) LUAJIT_MODE_FLUSHMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }
}
// metadata.generator-version: pni 21.0.0.17
// sha256:70e06b4e4c82e7ddef28341dc96c051a8afb3be16d74a552cea33d4c2af8d836
