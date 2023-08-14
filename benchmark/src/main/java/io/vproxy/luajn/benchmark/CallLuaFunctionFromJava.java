package io.vproxy.luajn.benchmark;

import io.vproxy.luajn.LuaJNFunction;
import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.LuaJNative;
import io.vproxy.pni.Allocator;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.foreign.Arena;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(java.util.concurrent.TimeUnit.NANOSECONDS)
public class CallLuaFunctionFromJava {
    private Allocator allocator;
    private LuaJNState L;
    private LuaJNFunction functionOneIntArgOnIntReturn;
    private LuaJNFunction functionNoArgOneIntReturn;
    private LuaJNFunction functionTwoIntArgsOneIntReturn;
    private LuaJNFunction functionNoArgTwoIntReturn;

    @Setup
    public void setUp() {
        LuaJNative.fullInit();

        allocator = Allocator.of(Arena.ofShared());
        L = new LuaJNState(allocator);
        functionOneIntArgOnIntReturn = L.loadFunction("return function(n) return 1 end");
        functionNoArgOneIntReturn = L.loadFunction("return function() return 1 end");
        functionTwoIntArgsOneIntReturn = L.loadFunction("return function(x, y) return 1 end");
        functionNoArgTwoIntReturn = L.loadFunction("return function() return 1, 2 end");
    }

    @TearDown
    public void tearDown() {
        functionOneIntArgOnIntReturn.close();
        L.close();
        allocator.close();
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void convenientOneIntArgOneIntReturn(Blackhole bh) {
        var res = (Number) functionOneIntArgOnIntReturn.invoke(10);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void stackOpOneIntArgOneIntReturn(Blackhole bh) {
        var res = (Number) functionOneIntArgOnIntReturn.invoke(L -> L.getLuaState().pushInteger(10))
            .convert(LL -> LL.getLuaState().toInteger(-1))
            .result();
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void convenientNoArgOneIntReturn(Blackhole bh) {
        var res = (Number) functionNoArgOneIntReturn.invoke();
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void stackOpNoArgOneIntReturn(Blackhole bh) {
        var res = (Number) functionNoArgOneIntReturn.invoke(L -> {
            })
            .convert(LL -> LL.getLuaState().toInteger(-1))
            .result();
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void convenientTwoIntArgsOneIntReturn(Blackhole bh) {
        var res = (Number) functionTwoIntArgsOneIntReturn.invoke(10, 20);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void stackOpTwoIntArgsOneIntReturn(Blackhole bh) {
        var res = (Number) functionTwoIntArgsOneIntReturn.invoke(L -> {
                L.getLuaState().pushInteger(10);
                L.getLuaState().pushInteger(20);
            })
            .convert(LL -> LL.getLuaState().toInteger(-1))
            .result();
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void convenientNoArgTwoIntReturn(Blackhole bh) {
        var res = (Object[]) functionNoArgTwoIntReturn.invoke();
        var ret0 = (Number) res[0];
        var ret1 = (Number) res[1];
        bh.consume(ret0);
        bh.consume(ret1);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void stackOpNoArgTwoIntReturn(Blackhole bh) {
        var res = (Object[]) functionNoArgTwoIntReturn.invoke(L -> {
            })
            .convert(LL -> LL.getLuaState().toInteger(-1))
            .convert(LL -> LL.getLuaState().toInteger(-1))
            .result();
        var ret0 = (Number) res[0];
        var ret1 = (Number) res[1];
        bh.consume(ret0);
        bh.consume(ret1);
    }
}
