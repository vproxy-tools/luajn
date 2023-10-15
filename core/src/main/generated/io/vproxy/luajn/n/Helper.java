package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Helper {
    private Helper() {
    }

    private static final Helper INSTANCE = new Helper();

    public static Helper get() {
        return INSTANCE;
    }

    private static final MethodHandle flushStdoutMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_Helper_flushStdout");

    public void flushStdout() {
        try {
            flushStdoutMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle memsetMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_Helper_memset", MemorySegment.class /* b */, int.class /* c */, long.class /* len */);

    public void memset(MemorySegment b, int c, long len) {
        try {
            memsetMH.invokeExact((MemorySegment) (b == null ? MemorySegment.NULL : b), c, len);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle temporaryVariablesTableIndexMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), MemorySegment.class, "JavaCritical_io_vproxy_luajn_n_Helper_temporaryVariablesTableIndex");

    public MemorySegment temporaryVariablesTableIndex() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) temporaryVariablesTableIndexMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle LUA_REGISTRYINDEXMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_REGISTRYINDEX");

    public int LUA_REGISTRYINDEX() {
        int RESULT;
        try {
            RESULT = (int) LUA_REGISTRYINDEXMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_ENVIRONINDEXMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_ENVIRONINDEX");

    public int LUA_ENVIRONINDEX() {
        int RESULT;
        try {
            RESULT = (int) LUA_ENVIRONINDEXMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_GLOBALSINDEXMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_GLOBALSINDEX");

    public int LUA_GLOBALSINDEX() {
        int RESULT;
        try {
            RESULT = (int) LUA_GLOBALSINDEXMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_YIELDMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_YIELD");

    public int LUA_YIELD() {
        int RESULT;
        try {
            RESULT = (int) LUA_YIELDMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_ERRRUNMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_ERRRUN");

    public int LUA_ERRRUN() {
        int RESULT;
        try {
            RESULT = (int) LUA_ERRRUNMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_ERRSYNTAXMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_ERRSYNTAX");

    public int LUA_ERRSYNTAX() {
        int RESULT;
        try {
            RESULT = (int) LUA_ERRSYNTAXMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_ERRMEMMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_ERRMEM");

    public int LUA_ERRMEM() {
        int RESULT;
        try {
            RESULT = (int) LUA_ERRMEMMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_ERRERRMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_ERRERR");

    public int LUA_ERRERR() {
        int RESULT;
        try {
            RESULT = (int) LUA_ERRERRMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_MULTRETMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_MULTRET");

    public int LUA_MULTRET() {
        int RESULT;
        try {
            RESULT = (int) LUA_MULTRETMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_TNONEMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_TNONE");

    public int LUA_TNONE() {
        int RESULT;
        try {
            RESULT = (int) LUA_TNONEMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_TNILMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_TNIL");

    public int LUA_TNIL() {
        int RESULT;
        try {
            RESULT = (int) LUA_TNILMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_TBOOLEANMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_TBOOLEAN");

    public int LUA_TBOOLEAN() {
        int RESULT;
        try {
            RESULT = (int) LUA_TBOOLEANMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_TLIGHTUSERDATAMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_TLIGHTUSERDATA");

    public int LUA_TLIGHTUSERDATA() {
        int RESULT;
        try {
            RESULT = (int) LUA_TLIGHTUSERDATAMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_TNUMBERMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_TNUMBER");

    public int LUA_TNUMBER() {
        int RESULT;
        try {
            RESULT = (int) LUA_TNUMBERMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_TSTRINGMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_TSTRING");

    public int LUA_TSTRING() {
        int RESULT;
        try {
            RESULT = (int) LUA_TSTRINGMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_TTABLEMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_TTABLE");

    public int LUA_TTABLE() {
        int RESULT;
        try {
            RESULT = (int) LUA_TTABLEMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_TFUNCTIONMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_TFUNCTION");

    public int LUA_TFUNCTION() {
        int RESULT;
        try {
            RESULT = (int) LUA_TFUNCTIONMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_TUSERDATAMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_TUSERDATA");

    public int LUA_TUSERDATA() {
        int RESULT;
        try {
            RESULT = (int) LUA_TUSERDATAMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_TTHREADMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_TTHREAD");

    public int LUA_TTHREAD() {
        int RESULT;
        try {
            RESULT = (int) LUA_TTHREADMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_MINSTACKMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_MINSTACK");

    public int LUA_MINSTACK() {
        int RESULT;
        try {
            RESULT = (int) LUA_MINSTACKMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_GCSTOPMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCSTOP");

    public int LUA_GCSTOP() {
        int RESULT;
        try {
            RESULT = (int) LUA_GCSTOPMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_GCRESTARTMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCRESTART");

    public int LUA_GCRESTART() {
        int RESULT;
        try {
            RESULT = (int) LUA_GCRESTARTMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_GCCOLLECTMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCCOLLECT");

    public int LUA_GCCOLLECT() {
        int RESULT;
        try {
            RESULT = (int) LUA_GCCOLLECTMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_GCCOUNTMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCCOUNT");

    public int LUA_GCCOUNT() {
        int RESULT;
        try {
            RESULT = (int) LUA_GCCOUNTMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_GCCOUNTBMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCCOUNTB");

    public int LUA_GCCOUNTB() {
        int RESULT;
        try {
            RESULT = (int) LUA_GCCOUNTBMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_GCSTEPMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCSTEP");

    public int LUA_GCSTEP() {
        int RESULT;
        try {
            RESULT = (int) LUA_GCSTEPMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_GCSETPAUSEMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCSETPAUSE");

    public int LUA_GCSETPAUSE() {
        int RESULT;
        try {
            RESULT = (int) LUA_GCSETPAUSEMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_GCSETSTEPMULMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_GCSETSTEPMUL");

    public int LUA_GCSETSTEPMUL() {
        int RESULT;
        try {
            RESULT = (int) LUA_GCSETSTEPMULMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_HOOKCALLMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_HOOKCALL");

    public int LUA_HOOKCALL() {
        int RESULT;
        try {
            RESULT = (int) LUA_HOOKCALLMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_HOOKRETMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_HOOKRET");

    public int LUA_HOOKRET() {
        int RESULT;
        try {
            RESULT = (int) LUA_HOOKRETMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_HOOKLINEMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_HOOKLINE");

    public int LUA_HOOKLINE() {
        int RESULT;
        try {
            RESULT = (int) LUA_HOOKLINEMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_HOOKCOUNTMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_HOOKCOUNT");

    public int LUA_HOOKCOUNT() {
        int RESULT;
        try {
            RESULT = (int) LUA_HOOKCOUNTMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_HOOKTAILRETMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_HOOKTAILRET");

    public int LUA_HOOKTAILRET() {
        int RESULT;
        try {
            RESULT = (int) LUA_HOOKTAILRETMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_MASKCALLMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_MASKCALL");

    public int LUA_MASKCALL() {
        int RESULT;
        try {
            RESULT = (int) LUA_MASKCALLMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_MASKRETMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_MASKRET");

    public int LUA_MASKRET() {
        int RESULT;
        try {
            RESULT = (int) LUA_MASKRETMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_MASKLINEMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_MASKLINE");

    public int LUA_MASKLINE() {
        int RESULT;
        try {
            RESULT = (int) LUA_MASKLINEMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle LUA_MASKCOUNTMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_Helper_LUA_MASKCOUNT");

    public int LUA_MASKCOUNT() {
        int RESULT;
        try {
            RESULT = (int) LUA_MASKCOUNTMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }
}
// metadata.generator-version: pni 21.0.0.17
// sha256:f4ee84db17120a4772dc979b175b2bfc5ef139adeff95e2ddbffd5cae200a856
