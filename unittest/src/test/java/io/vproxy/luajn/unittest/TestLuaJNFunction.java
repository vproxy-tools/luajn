package io.vproxy.luajn.unittest;

import io.vproxy.luajn.LuaJNFunction;
import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.LuaJNTable;
import io.vproxy.luajn.LuaJNUserData;
import io.vproxy.luajn.n.LuaState;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.CallSite;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.PNIString;
import org.junit.Test;

import java.lang.foreign.MemorySegment;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings({"RemoveUnusedLocal"})
public class TestLuaJNFunction {
    @Test
    public void call() {
        // language="lua"
        var L = Utils.create("""
            function f(a, b, c)
                return a + b, b + c, c + a
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            var res = (Object[]) f.invoke(LL -> {
                    LL.getLuaState().pushInteger(2);
                    LL.getLuaState().pushInteger(3);
                    LL.getLuaState().pushInteger(4);
                })
                .convert(LL -> LL.getLuaState().toNumber(-1))
                .convert(LL -> LL.getLuaState().toNumber(-1))
                .convert(LL -> LL.getLuaState().toNumber(-1))
                .result();
            Utils.postOpCheck(L);

            assertEquals(5, ((Number) res[0]).intValue());
            assertEquals(7, ((Number) res[1]).intValue());
            assertEquals(6, ((Number) res[2]).intValue());
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgNil() {
        // language="lua"
        var L = Utils.create("""
            function f(n)
                return n == nil
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            var res = (boolean) f.invoke((Object) null);
            Utils.postOpCheck(L);
            assertTrue(res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgInt() {
        // language="lua"
        var L = Utils.create("""
            function f(n)
                return n + 1
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            var res = (Number) f.invoke(2);
            Utils.postOpCheck(L);
            assertEquals(3, res.intValue());
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgLong() {
        // language="lua"
        var L = Utils.create("""
            function f(n)
                return n + 2
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            var res = (Number) f.invoke(3L);
            Utils.postOpCheck(L);
            assertEquals(5, res.intValue());
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgDouble() {
        // language="lua"
        var L = Utils.create("""
            function f(n)
                return n + 3.2
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            var res = (Number) f.invoke(12.8);
            Utils.postOpCheck(L);
            assertEquals(16.0, res.doubleValue(), 0);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgString() {
        // language="lua"
        var L = Utils.create("""
            function f(n)
                return "prefix-" .. n .. "-suffix"
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            var res = (String) f.invoke("abc");
            Utils.postOpCheck(L);
            assertEquals("prefix-abc-suffix", res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgTable() {
        // language="lua"
        var L = Utils.create("""
            function f(t)
                return t.a, t.b
            end
            """);
        try (var t = L.newTable(); var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            t.put("a", 12);
            t.put("b", 3.2);
            var res = (Object[]) f.invoke(t);
            Utils.postOpCheck(L);
            assertEquals(12, ((Number) res[0]).intValue());
            assertEquals(3.2, ((Number) res[1]).doubleValue(), 0);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgFunction() {
        // language="lua"
        var L = Utils.create("""
            function x(n)
                return n + 2
            end
            function f(ff)
                return ff(1)
            end
            """);
        try (var x = L.getGlobal().getFunction("x"); var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            var res = (Number) f.invoke(x);
            Utils.postOpCheck(L);
            assertEquals(3, res.intValue());
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgBool() {
        // language="lua"
        var L = Utils.create("""
            function f(n)
                return not n
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            var res = (boolean) f.invoke(true);
            Utils.postOpCheck(L);
            assertFalse(res);
            res = (boolean) f.invoke(false);
            Utils.postOpCheck(L);
            assertTrue(res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgLightUserData() {
        // language="lua"
        var L = Utils.create("""
            local luajn = require "luajn"
            function f(func, obj)
                local n = luajn.upcall(func, obj)
                luajn.release_func(func)
                return n
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            var func = LuaState.Func.of(LL -> {
                assertNotNull(LL);
                return 123;
            });

            var res = (Number) f.invoke(func.MEMORY, L.getLuaState().MEMORY);
            Utils.postOpCheck(L);
            assertEquals(123, res.intValue());
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgCoroutine() {
        // language="lua"
        var L = Utils.create("""
            function f(co)
                return coroutine.resume(co)
            end
            function x(co)
                coroutine.yield(1)
            end
            """);
        try (var x = L.getGlobal().getFunction("x"); var f = L.getGlobal().getFunction("f"); var allocator = Allocator.ofConfined()) {
            Utils.postOpCheck(L);
            try (var T = L.newCoroutine(allocator)) {
                Utils.postOpCheck(L);
                Utils.postOpCheck(T);
                x.__push(T);
                Utils.postOpCheck(L);
                Utils.postOpCheck(T, 1);

                var res = (Object[]) f.invoke((Object) T);
                assertTrue((boolean) res[0]);
                assertEquals(1, ((Number) res[1]).intValue());
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgAndReturnUserData() {
        // language="lua"
        var L = Utils.create("""
            function f(x)
                return x
            end
            """);
        try (var f = L.getGlobal().getFunction("f"); var data = L.newUserData(10)) {
            Utils.postOpCheck(L);
            data.MEMORY.setUtf8String(0, "hello");
            var res = (LuaJNUserData) f.invoke(data);
            Utils.postOpCheck(L);
            assertEquals(res.MEMORY.address(), data.MEMORY.address());
            assertEquals("hello", res.MEMORY.reinterpret(10).getUtf8String(0));
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgFunc() {
        // language="lua"
        var L = Utils.create("""
            local luajn = require("luajn")
            function f(func, obj)
                return luajn.upcall(func, obj)
            end
            """);
        try (var f = L.getGlobal().getFunction("f"); var userdata = L.newUserData(LuaState.LAYOUT.byteSize())) {
            Utils.postOpCheck(L);

            var testFunc = LuaState.Func.of(LL -> {
                assertNotNull(LL);
                return 123;
            });
            var res = (Number) f.invoke(testFunc, userdata);
            Utils.postOpCheck(L);
            assertEquals(123, res.intValue());
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgPNIRef() {
        // language="lua"
        var L = Utils.create("""
            local luajn = require("luajn")
            function f(func, obj)
                return luajn.upcall(func, obj)
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);

            var ls = new ArrayList<String>();
            var ref = PNIRef.of(ls);
            ls.add("hello");
            var testFunc = PNIRef.Func.<List<String>>of(ll -> {
                ll.add("world");
                return 0;
            });
            var res = (Number) f.invoke(testFunc, ref);
            Utils.postOpCheck(L);
            assertEquals(0, res.intValue());
            assertEquals(List.of("hello", "world"), ls);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeArgCallSite() {
        // language="lua"
        var L = Utils.create("""
            function f(func)
                return func()
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);

            //noinspection Convert2Lambda
            var res = (String) f.invoke(new CallSite<LuaState>() {
                @Override
                public int call(LuaState L) {
                    PNIString.temporary("hello world", L::pushString);
                    return 1;
                }
            });
            Utils.postOpCheck(L);
            assertEquals("hello world", res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeReturnNumber() {
        // language="lua"
        var L = Utils.create("""
            function f1()
                return 1
            end
            function f2()
                return 3.2
            end
            """);
        try (var f1 = L.getGlobal().getFunction("f1"); var f2 = L.getGlobal().getFunction("f2")) {
            Utils.postOpCheck(L);
            var res = (Number) f1.invoke();
            Utils.postOpCheck(L);
            assertEquals(1, res.intValue());
            res = (Number) f2.invoke();
            Utils.postOpCheck(L);
            assertEquals(3.2, res.doubleValue(), 0);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeReturnString() {
        // language="lua"
        var L = Utils.create("""
            function f()
                return "hello world"
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            var res = (String) f.invoke();
            Utils.postOpCheck(L);
            assertEquals("hello world", res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeReturnNil() {
        // language="lua"
        var L = Utils.create("""
            function f()
                return nil
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            var res = f.invoke();
            Utils.postOpCheck(L);
            assertNull(res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeReturnTable() {
        // language="lua"
        var L = Utils.create("""
            function f()
                return {a = 1, b = 2}
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            try (var res = (LuaJNTable) f.invoke()) {
                Utils.postOpCheck(L);
                assertEquals(1L, res.getLong("a"));
                Utils.postOpCheck(L);
                assertEquals(2L, res.getLong("b"));
                Utils.postOpCheck(L);
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeReturnFunction() {
        // language="lua"
        var L = Utils.create("""
            function f()
                return function(x)
                    return x + 1
                end
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            try (var res = (LuaJNFunction) f.invoke()) {
                Utils.postOpCheck(L);
                var nres = (Number) res.invoke(10);
                Utils.postOpCheck(L);
                assertEquals(11, nres.intValue());
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeReturnBool() {
        // language="lua"
        var L = Utils.create("""
            function f1()
                return true
            end
            function f2()
                return false
            end
            """);
        try (var f1 = L.getGlobal().getFunction("f1"); var f2 = L.getGlobal().getFunction("f2")) {
            Utils.postOpCheck(L);
            var res = (boolean) f1.invoke();
            Utils.postOpCheck(L);
            assertTrue(res);
            res = (boolean) f2.invoke();
            Utils.postOpCheck(L);
            assertFalse(res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeReturnCoroutine() {
        // language="lua"
        var L = Utils.create("""
            function f()
                return coroutine.create(function(x)
                    coroutine.yield(x + 1)
                end)
            end
            """);
        try (var f = L.getGlobal().getFunction("f"); var allocator = Allocator.ofConfined()) {
            Utils.postOpCheck(L);
            try (var res = (LuaJNState) f.invoke(allocator)) {
                Utils.postOpCheck(L);
                var nres = (Number) res.resume(10);
                Utils.postOpCheck(L);
                assertEquals(11, nres.intValue());
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void invokeReturnLightUserData() {
        // language="lua"
        var L = Utils.create("""
            function f(x)
                return x
            end
            """);
        try (var f = L.getGlobal().getFunction("f"); var allocator = Allocator.ofConfined()) {
            Utils.postOpCheck(L);
            var mem = allocator.allocate(10);
            mem.setUtf8String(0, "hello");
            var res = (MemorySegment) f.invoke(mem);
            Utils.postOpCheck(L);
            assertEquals(res.address(), mem.address());
            assertEquals("hello", res.reinterpret(10).getUtf8String(0));
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void multipleArgs() {
        // language="lua"
        var L = Utils.create("""
            function f(a, b, c, d, e)
                return a + b * c - d / e
            end
            """);
        try (var f = L.getGlobal().getFunction("f")) {
            Utils.postOpCheck(L);
            var res = (Number) f.invoke(1, 2, 3, 8, 4);
            Utils.postOpCheck(L);
            assertEquals(5, res.intValue());
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void multipleReturns() {
        // language="lua"
        var L = Utils.create("""
            function f(lud, ud)
                return 1,
                       3.2,
                       "abc",
                       nil,
                       {a = 1, b = 2},
                       function() return 1 end,
                       true,
                       false,
                       lud,
                       ud,
                       coroutine.create(function() coroutine.yield(1) end)
            end
            """);
        try (var f = L.getGlobal().getFunction("f"); var allocator = Allocator.ofConfined(); var data = L.newUserData(10)) {
            Utils.postOpCheck(L);
            var mem = allocator.allocate(10);
            mem.setUtf8String(0, "hello");
            data.MEMORY.setUtf8String(0, "world");

            var res = (Object[]) f.invoke(allocator, mem, data);
            Utils.postOpCheck(L);

            assertEquals(1, ((Number) res[0]).intValue());
            assertEquals(3.2, ((Number) res[1]).doubleValue(), 0);
            assertEquals("abc", res[2]);
            assertNull(res[3]);
            try (var table = (LuaJNTable) res[4]) {
                assertEquals(1, table.getLong("a"));
                Utils.postOpCheck(L);
                assertEquals(2, table.getLong("b"));
                Utils.postOpCheck(L);
            }
            Utils.postOpCheck(L);

            try (var func = (LuaJNFunction) res[5]) {
                var n = (Number) func.invoke();
                Utils.postOpCheck(L);
                assertEquals(1, n.intValue());
            }
            Utils.postOpCheck(L);

            assertTrue((boolean) res[6]);
            assertFalse((boolean) res[7]);
            var memRes = (MemorySegment) res[8];
            assertEquals(mem.address(), memRes.address());
            assertEquals("hello", memRes.reinterpret(10).getUtf8String(0));
            var resData = (LuaJNUserData) res[9];
            assertEquals(data.MEMORY.address(), resData.MEMORY.address());
            assertEquals("world", resData.MEMORY.reinterpret(10).getUtf8String(0));
            try (var co = (LuaJNState) res[10]) {
                var n = co.resume();
                Utils.postOpCheck(L);
                assertEquals(1, ((Number) n).intValue());
            }
            Utils.postOpCheck(L);

            assertEquals(11, res.length);
        }
        Utils.postOpCheck(L);
    }
}
