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
public class CallCallSiteFromLua {
    private Allocator allocator;
    private LuaJNState L;
    private LuaJNFunction functionNoArgIntReturn;
    private LuaJNFunction functionOneIntArgIntReturn;

    @Setup
    public void setUp() {
        LuaJNative.fullInit();

        allocator = Allocator.of(Arena.ofShared());
        L = new LuaJNState(allocator);
        functionNoArgIntReturn = L.loadFunction("return function() return f1() end");
        functionOneIntArgIntReturn = L.loadFunction("return function() return f2(1) end");

        L.getGlobal().put("f1", LL -> {
            LL.pushInteger(10);
            return 1;
        });
        L.getGlobal().put("f2", LL -> {
            var n = LL.toInteger(1);
            LL.pushNumber(n);
            return 1;
        });
    }

    @TearDown
    public void tearDown() {
        functionNoArgIntReturn.close();
        functionOneIntArgIntReturn.close();
        L.close();
        allocator.close();
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void noArgIntReturn(Blackhole bh) {
        var res = (Number) functionNoArgIntReturn.invoke();
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void oneIntArgIntReturn(Blackhole bh) {
        var res = (Number) functionOneIntArgIntReturn.invoke();
        bh.consume(res);
    }
}
