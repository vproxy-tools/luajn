package io.vproxy.luajn.benchmark;

import io.vproxy.luajn.LuaJNFunction;
import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.PanamaUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(java.util.concurrent.TimeUnit.NANOSECONDS)
public class CallJavaMethodFromLua {
    private Arena arena;
    private LuaJNState L;
    private LuaJNFunction ffiOneIntArgIntReturn;
    private LuaJNFunction ffiOneIntArgIntReturnPNIUpcall;
    private LuaJNFunction ffiTwoIntArgsIntReturn;
    private LuaJNFunction ffiTwoIntArgsIntReturnPNIUpcall;
    private LuaJNFunction ffiOneRefArgIntReturn;
    private LuaJNFunction ffiOneRefArgIntReturnPNIUpcall;

    @Setup
    public void setUp() {
        System.loadLibrary("luajn-benchmark");
        BenchmarkUpcall.setImpl(BenchmarkUpcallImpl.get());

        LuaJNative.fullInit();

        arena = Arena.ofShared();
        var allocator = Allocator.of(arena);
        L = new LuaJNState(allocator);
        LuaLib.get().openLibs(L.getLuaState());

        var ffiOneIntArgIntReturnJavaFunc = PanamaUtils.defineCFunctionByName(arena, CallJavaMethodFromLua.class, "ffiOneIntArgIntReturnJavaFunc");
        var ffiTwoIntArgsIntReturnJavaFunc = PanamaUtils.defineCFunctionByName(arena, CallJavaMethodFromLua.class, "ffiTwoIntArgsIntReturnJavaFunc");
        var ffiOneRefArgIntReturnJavaFunc = PanamaUtils.defineCFunctionByName(arena, CallJavaMethodFromLua.class, "ffiOneRefArgIntReturnJavaFunc");

        L.getGlobal().put("ffiOneIntArgIntReturnJavaFunc", ffiOneIntArgIntReturnJavaFunc);
        L.getGlobal().put("ffiTwoIntArgsIntReturnJavaFunc", ffiTwoIntArgsIntReturnJavaFunc);
        L.getGlobal().put("ffiOneRefArgIntReturnJavaFunc", ffiOneRefArgIntReturnJavaFunc);
        L.getGlobal().put("ref", PNIRef.of(1));

        // language="lua"
        L.load("""
            local ffi = require("ffi")
            ffi.cdef[[
            typedef int (*ffiOneIntArgIntReturnPtr)(int);
            typedef int (*ffiTwoIntArgsIntReturnPtr)(int, int);
            typedef int (*ffiOneRefArgIntReturnPtr)(void*);
            int JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_oneIntArgIntReturn(int);
            int JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_twoIntArgIntReturn(int,int);
            int JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_oneRefArgIntReturn(void*);
            ]]
            ffiOneIntArgIntReturnFunc = ffi.cast("ffiOneIntArgIntReturnPtr", ffiOneIntArgIntReturnJavaFunc)
            ffiTwoIntArgsIntReturnFunc = ffi.cast("ffiTwoIntArgsIntReturnPtr", ffiTwoIntArgsIntReturnJavaFunc)
            ffiOneRefArgIntReturnFunc = ffi.cast("ffiOneRefArgIntReturnPtr", ffiOneRefArgIntReturnJavaFunc)
            ffiOneIntArgIntReturnPNIUpcallFunc = ffi.C.JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_oneIntArgIntReturn
            ffiTwoIntArgsIntReturnPNIUpcallFunc = ffi.C.JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_twoIntArgIntReturn
            ffiOneRefArgIntReturnPNIUpcallFunc = ffi.C.JavaCritical_io_vproxy_luajn_benchmark_BenchmarkUpcall_oneRefArgIntReturn
                        
            function ffiOneIntArgIntReturn()
                return ffiOneIntArgIntReturnFunc(1)
            end
            function ffiTwoIntArgsIntReturn()
                return ffiTwoIntArgsIntReturnFunc(1, 2)
            end
            function ffiOneRefArgIntReturn()
                return ffiOneRefArgIntReturnFunc(ref)
            end
            function ffiOneIntArgIntReturnPNIUpcall()
                return ffiOneIntArgIntReturnPNIUpcallFunc(1)
            end
            function ffiTwoIntArgsIntReturnPNIUpcall()
                return ffiTwoIntArgsIntReturnPNIUpcallFunc(1, 2)
            end
            function ffiOneRefArgIntReturnPNIUpcall()
                return ffiOneRefArgIntReturnPNIUpcallFunc(ref)
            end
            """);

        ffiOneIntArgIntReturn = L.getGlobal().getFunction("ffiOneIntArgIntReturn");
        ffiTwoIntArgsIntReturn = L.getGlobal().getFunction("ffiTwoIntArgsIntReturn");
        ffiOneRefArgIntReturn = L.getGlobal().getFunction("ffiOneRefArgIntReturn");
        ffiOneIntArgIntReturnPNIUpcall = L.getGlobal().getFunction("ffiOneIntArgIntReturnPNIUpcall");
        ffiTwoIntArgsIntReturnPNIUpcall = L.getGlobal().getFunction("ffiTwoIntArgsIntReturnPNIUpcall");
        ffiOneRefArgIntReturnPNIUpcall = L.getGlobal().getFunction("ffiOneRefArgIntReturnPNIUpcall");
    }

    @TearDown
    public void tearDown() {
        ffiOneIntArgIntReturn.close();
        ffiTwoIntArgsIntReturn.close();
        ffiOneRefArgIntReturn.close();
        ffiOneIntArgIntReturnPNIUpcall.close();
        ffiTwoIntArgsIntReturnPNIUpcall.close();
        ffiOneRefArgIntReturnPNIUpcall.close();
        L.close();
        arena.close();
    }

    @SuppressWarnings("unused")
    public static int ffiOneIntArgIntReturnJavaFunc(int n) {
        return n;
    }

    @SuppressWarnings("unused")
    public static int ffiTwoIntArgsIntReturnJavaFunc(int a, int b) {
        return a + b;
    }

    @SuppressWarnings("unused")
    public static int ffiOneRefArgIntReturnJavaFunc(MemorySegment mem) {
        return PNIRef.getRef(mem);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void ffiOneIntArgIntReturn(Blackhole bh) {
        var res = (Number) ffiOneIntArgIntReturn.invoke();
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void ffiTwoIntArgsIntReturn(Blackhole bh) {
        var res = (Number) ffiTwoIntArgsIntReturn.invoke();
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void ffiOneRefArgIntReturn(Blackhole bh) {
        var res = (Number) ffiOneRefArgIntReturn.invoke();
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void ffiOneIntArgIntReturnPNIUpcall(Blackhole bh) {
        var res = (Number) ffiOneIntArgIntReturnPNIUpcall.invoke();
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void ffiTwoIntArgsIntReturnPNIUpcall(Blackhole bh) {
        var res = (Number) ffiTwoIntArgsIntReturnPNIUpcall.invoke();
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void ffiOneRefArgIntReturnPNIUpcall(Blackhole bh) {
        var res = (Number) ffiOneRefArgIntReturnPNIUpcall.invoke();
        bh.consume(res);
    }
}
