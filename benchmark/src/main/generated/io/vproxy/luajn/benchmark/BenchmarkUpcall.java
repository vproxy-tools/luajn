package io.vproxy.luajn.benchmark;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class BenchmarkUpcall {
    private static final Arena ARENA = Arena.ofShared();

    public static final MemorySegment oneIntArgIntReturn;

    private static int oneIntArgIntReturn(int a) {
        if (IMPL == null) {
            System.out.println("io.vproxy.luajn.benchmark.BenchmarkUpcall#oneIntArgIntReturn");
            System.exit(1);
        }
        var RESULT = IMPL.oneIntArgIntReturn(
            a
        );
        return RESULT;
    }

    public static final MemorySegment twoIntArgIntReturn;

    private static int twoIntArgIntReturn(int a, int b) {
        if (IMPL == null) {
            System.out.println("io.vproxy.luajn.benchmark.BenchmarkUpcall#twoIntArgIntReturn");
            System.exit(1);
        }
        var RESULT = IMPL.twoIntArgIntReturn(
            a,
            b
        );
        return RESULT;
    }

    public static final MemorySegment oneRefArgIntReturn;

    private static int oneRefArgIntReturn(MemorySegment ref) {
        if (IMPL == null) {
            System.out.println("io.vproxy.luajn.benchmark.BenchmarkUpcall#oneRefArgIntReturn");
            System.exit(1);
        }
        var RESULT = IMPL.oneRefArgIntReturn(
            (ref.address() == 0 ? null : PNIRef.getRef(ref))
        );
        return RESULT;
    }

    static {
        MethodHandle oneIntArgIntReturnMH;
        MethodHandle twoIntArgIntReturnMH;
        MethodHandle oneRefArgIntReturnMH;
        try {
            oneIntArgIntReturnMH = MethodHandles.lookup().findStatic(io.vproxy.luajn.benchmark.BenchmarkUpcall.class, "oneIntArgIntReturn", MethodType.methodType(int.class, int.class));
            twoIntArgIntReturnMH = MethodHandles.lookup().findStatic(io.vproxy.luajn.benchmark.BenchmarkUpcall.class, "twoIntArgIntReturn", MethodType.methodType(int.class, int.class, int.class));
            oneRefArgIntReturnMH = MethodHandles.lookup().findStatic(io.vproxy.luajn.benchmark.BenchmarkUpcall.class, "oneRefArgIntReturn", MethodType.methodType(int.class, MemorySegment.class));
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        oneIntArgIntReturn = PanamaUtils.defineCFunction(ARENA, oneIntArgIntReturnMH, int.class, int.class);
        twoIntArgIntReturn = PanamaUtils.defineCFunction(ARENA, twoIntArgIntReturnMH, int.class, int.class, int.class);
        oneRefArgIntReturn = PanamaUtils.defineCFunction(ARENA, oneRefArgIntReturnMH, int.class, MemorySegment.class);

        var initMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_INIT", MemorySegment.class, MemorySegment.class, MemorySegment.class);
        try {
            initMH.invoke(oneIntArgIntReturn, twoIntArgIntReturn, oneRefArgIntReturn);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private static Interface IMPL = null;

    public static void setImpl(Interface impl) {
        java.util.Objects.requireNonNull(impl);
        IMPL = impl;
    }

    public interface Interface {
        int oneIntArgIntReturn(int a);

        int twoIntArgIntReturn(int a, int b);

        int oneRefArgIntReturn(java.lang.Integer ref);
    }
}
// metadata.generator-version: pni 21.0.0.8
// sha256:a1a57313e69f6b14abf4cdec0ca0693c0499aeae9eb87b9dd9f88b9ab19b3754
