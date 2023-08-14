package io.vproxy.luajn.benchmark;

import io.vproxy.luajn.LuaJNFunction;
import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIFunc;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.foreign.Arena;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(java.util.concurrent.TimeUnit.NANOSECONDS)
public class CallPNIFuncFromLua {
    private Allocator allocator;
    private LuaJNState L;
    private LuaJNFunction normal;
    private LuaJNFunction ffi;

    @Setup
    public void setUp() {
        LuaJNative.fullInit();

        allocator = Allocator.of(Arena.ofShared());
        L = new LuaJNState(allocator);
        LuaLib.get().openLibs(L.getLuaState());

        // language="lua"
        L.load("""
            local luajn = require("luajn")
            local luajn_ffi = require("luajn_ffi")
            function normal()
                return luajn.upcall(func, nil)
            end
            function ffi()
                return luajn_ffi.upcall(func, nil)
            end
            """);

        L.getGlobal().put("func", PNIFunc.VoidFunc.of(v -> 1));
        normal = L.getGlobal().getFunction("normal");
        ffi = L.getGlobal().getFunction("ffi");
    }

    @TearDown
    public void tearDown() {
        normal.close();
        ffi.close();
        L.close();
        allocator.close();
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void normal(Blackhole bh) {
        var res = (Number) normal.invoke();
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void ffi(Blackhole bh) {
        var res = (Number) ffi.invoke();
        bh.consume(res);
    }
}
