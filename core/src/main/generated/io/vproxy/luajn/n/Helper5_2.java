package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Helper5_2 {
    private Helper5_2() {
    }

    private static final Helper5_2 INSTANCE = new Helper5_2();

    public static Helper5_2 get() {
        return INSTANCE;
    }

    private static final MethodHandle LUA_GCISRUNNINGMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_Helper5_2_LUA_GCISRUNNING");

    public int LUA_GCISRUNNING() {
        int RESULT;
        try {
            RESULT = (int) LUA_GCISRUNNINGMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_OKMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_Helper5_2_LUA_OK");

    public int LUA_OK() {
        int RESULT;
        try {
            RESULT = (int) LUA_OKMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_RIDX_GLOBALSMH = PanamaUtils.lookupPNICriticalFunction(true, int.class, "JavaCritical_io_vproxy_luajn_n_Helper5_2_LUA_RIDX_GLOBALS");

    public int LUA_RIDX_GLOBALS() {
        int RESULT;
        try {
            RESULT = (int) LUA_RIDX_GLOBALSMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }
}
// metadata.generator-version: pni 21.0.0.8
// sha256:55884a470acafaf749eea8b2119ecb6b5a105130238f75e79e8e59be7d982413
