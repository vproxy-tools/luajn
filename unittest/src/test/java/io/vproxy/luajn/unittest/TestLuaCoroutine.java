package io.vproxy.luajn.unittest;

import io.vproxy.luajn.n.Consts;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIString;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings({"RemoveUnusedLocal"})
public class TestLuaCoroutine {
    @Test
    public void simple() {
        // language="lua"
        var L = Utils.create("""
            function co(a, b, c)
                local x, y = coroutine.yield(a + b, b + c)
                local z = coroutine.yield("a-" .. x, y .. "-b")
                return z
            end
            """);
        try (var co = L.getGlobal().getFunction("co"); var coL = L.newCoroutine(Utils.GlobalAllocator)) {
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);

            var res1 = (Object[]) co.invoke(coL, 5, 6, 7);
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
            assertEquals(11, ((Number) res1[0]).intValue());
            assertEquals(13, ((Number) res1[1]).intValue());
            assertEquals(Consts.LUA_YIELD, coL.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);

            // NOTE: the co routine should be resumed without the function
            var res2 = (Object[]) coL.resume("hello", "world");
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
            assertEquals("a-hello", res2[0]);
            assertEquals("world-b", res2[1]);
            assertEquals(Consts.LUA_YIELD, coL.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);

            // NOTE: the co routine should be resumed without the function
            var res3 = coL.resume(3.2);
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
            assertEquals(3.2, ((Number) res3).doubleValue(), 0);
            assertEquals(0, coL.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void stack() {
        // language="lua"
        var L = Utils.create("""
            function co(a, b, c)
                local x, y = coroutine.yield(a + b, b + c)
                local z = coroutine.yield("a-" .. x, y .. "-b")
                return z
            end
            """);
        try (var co = L.getGlobal().getFunction("co"); var coL = L.newCoroutine(Utils.GlobalAllocator)) {
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);

            var res1 = (Object[]) co.invoke(coL, LL -> {
                    LL.getLuaState().pushInteger(5);
                    LL.getLuaState().pushInteger(6);
                    LL.getLuaState().pushInteger(7);
                })
                .convert(LL -> LL.getLuaState().toNumber(-1))
                .convert(LL -> LL.getLuaState().toNumber(-1))
                .result();
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
            assertEquals(11, ((Number) res1[0]).intValue());
            assertEquals(13, ((Number) res1[1]).intValue());
            assertEquals(Consts.LUA_YIELD, coL.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);

            // NOTE: the co routine should be resumed without the function
            var call2 = (Object[]) coL.resume(LL -> {
                    try (var allocator = Allocator.ofConfined()) {
                        LL.getLuaState().pushString(new PNIString(allocator, "hello"));
                        LL.getLuaState().pushString(new PNIString(allocator, "world"));
                    }
                })
                .convert(LL -> LL.getLuaState().toString(-1).toString())
                .convert(LL -> LL.getLuaState().toString(-1).toString())
                .result();
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
            assertEquals("a-hello", call2[0]);
            assertEquals("world-b", call2[1]);
            assertEquals(Consts.LUA_YIELD, coL.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);

            // NOTE: the co routine should be resumed without the function
            var res3 = (double) coL.resume(LL -> LL.getLuaState().pushNumber(3.2))
                .convert(LL -> LL.getLuaState().toNumber(-1))
                .result();
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
            assertEquals(3.2, res3, 0);
            assertEquals(0, coL.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void simpleRetrieveFuncFromThread() {
        // language="lua"
        var L = Utils.create("""
            function co(a, b, c)
                local x, y = coroutine.yield(a + b, b + c)
                local z = coroutine.yield("a-" .. x, y .. "-b")
                return z
            end
            """);
        try (var coL = L.newCoroutine(Utils.GlobalAllocator); var co = coL.getGlobal().getFunction("co")) {
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);

            var res1 = (Object[]) co.invoke(5, 6, 7);
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
            assertEquals(11, ((Number) res1[0]).intValue());
            assertEquals(13, ((Number) res1[1]).intValue());
            assertEquals(Consts.LUA_YIELD, coL.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);

            // NOTE: the co routine should be resumed without the function
            var res2 = (Object[]) coL.resume("hello", "world");
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
            assertEquals("a-hello", res2[0]);
            assertEquals("world-b", res2[1]);
            assertEquals(Consts.LUA_YIELD, coL.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);

            // NOTE: the co routine should be resumed without the function
            var res3 = coL.resume(3.2);
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
            assertEquals(3.2, ((Number) res3).doubleValue(), 0);
            assertEquals(0, coL.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void stackRetrieveFuncFromThread() {
        // language="lua"
        var L = Utils.create("""
            function co(a, b, c)
                local x, y = coroutine.yield(a + b, b + c)
                local z = coroutine.yield("a-" .. x, y .. "-b")
                return z
            end
            """);
        try (var coL = L.newCoroutine(Utils.GlobalAllocator); var co = coL.getGlobal().getFunction("co")) {
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);

            var res1 = (Object[]) co.invoke(LL -> {
                    LL.getLuaState().pushInteger(5);
                    LL.getLuaState().pushInteger(6);
                    LL.getLuaState().pushInteger(7);
                })
                .convert(LL -> LL.getLuaState().toNumber(-1))
                .convert(LL -> LL.getLuaState().toNumber(-1))
                .result();
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
            assertEquals(11, ((Number) res1[0]).intValue());
            assertEquals(13, ((Number) res1[1]).intValue());
            assertEquals(Consts.LUA_YIELD, coL.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);

            // NOTE: the co routine should be resumed without the function
            var call2 = (Object[]) coL.resume(LL -> {
                    try (var allocator = Allocator.ofConfined()) {
                        LL.getLuaState().pushString(new PNIString(allocator, "hello"));
                        LL.getLuaState().pushString(new PNIString(allocator, "world"));
                    }
                })
                .convert(LL -> LL.getLuaState().toString(-1).toString())
                .convert(LL -> LL.getLuaState().toString(-1).toString())
                .result();
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
            assertEquals("a-hello", call2[0]);
            assertEquals("world-b", call2[1]);
            assertEquals(Consts.LUA_YIELD, coL.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);

            // NOTE: the co routine should be resumed without the function
            var res3 = (double) coL.resume(LL -> LL.getLuaState().pushNumber(3.2))
                .convert(LL -> LL.getLuaState().toNumber(-1))
                .result();
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
            assertEquals(3.2, res3, 0);
            assertEquals(0, coL.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(coL);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void simpleThread() {
        // language="lua"
        var L = Utils.create("""
            co = coroutine.create(function(a, b, c)
                local x, y = coroutine.yield(a + b, b + c)
                local z = coroutine.yield("a-" .. x, y .. "-b")
                return z
            end)
            """);
        try (var allocator = Allocator.ofConfined(); var co = L.getGlobal().getCoroutine("co", allocator)) {
            Utils.postOpCheck(L);
            Utils.postOpCheck(co, 1);

            var res1 = (Object[]) co.resume(5, 6, 7);
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
            assertEquals(11, ((Number) res1[0]).intValue());
            assertEquals(13, ((Number) res1[1]).intValue());
            assertEquals(Consts.LUA_YIELD, co.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
        }

        try (var allocator = Allocator.ofConfined(); var co = L.getGlobal().getCoroutine("co", allocator)) {
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);

            var res2 = (Object[]) co.resume("hello", "world");
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
            assertEquals("a-hello", res2[0]);
            assertEquals("world-b", res2[1]);
            assertEquals(Consts.LUA_YIELD, co.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
        }

        try (var allocator = Allocator.ofConfined(); var co = L.getGlobal().getCoroutine("co", allocator)) {
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);

            var res3 = co.resume(3.2);
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
            assertEquals(3.2, ((Number) res3).doubleValue(), 0);
            assertEquals(0, co.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void stackThread() {
        // language="lua"
        var L = Utils.create("""
            co = coroutine.create(function(a, b, c)
                local x, y = coroutine.yield(a + b, b + c)
                local z = coroutine.yield("a-" .. x, y .. "-b")
                return z
            end)
            """);
        try (var allocator = Allocator.ofConfined(); var co = L.getGlobal().getCoroutine("co", allocator)) {
            Utils.postOpCheck(L);
            Utils.postOpCheck(co, 1);

            var res1 = (Object[]) co.resume(LL -> {
                    LL.getLuaState().pushInteger(5);
                    LL.getLuaState().pushInteger(6);
                    LL.getLuaState().pushInteger(7);
                })
                .convert(LL -> LL.getLuaState().toNumber(-1))
                .convert(LL -> LL.getLuaState().toNumber(-1))
                .result();
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
            assertEquals(11, ((Number) res1[0]).intValue());
            assertEquals(13, ((Number) res1[1]).intValue());
            assertEquals(Consts.LUA_YIELD, co.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
        }

        try (var allocator = Allocator.ofConfined(); var co = L.getGlobal().getCoroutine("co", allocator)) {
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);

            var call2 = (Object[]) co.resume(LL -> {
                    LL.getLuaState().pushString(new PNIString(allocator, "hello"));
                    LL.getLuaState().pushString(new PNIString(allocator, "world"));
                })
                .convert(LL -> LL.getLuaState().toString(-1).toString())
                .convert(LL -> LL.getLuaState().toString(-1).toString())
                .result();
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
            assertEquals("a-hello", call2[0]);
            assertEquals("world-b", call2[1]);
            assertEquals(Consts.LUA_YIELD, co.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
        }

        try (var allocator = Allocator.ofConfined(); var co = L.getGlobal().getCoroutine("co", allocator)) {
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);

            var res3 = (double) co.resume(LL -> LL.getLuaState().pushNumber(3.2))
                .convert(LL -> LL.getLuaState().toNumber(-1))
                .result();
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
            assertEquals(3.2, res3, 0);
            assertEquals(0, co.getStatus());
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
        }
        Utils.postOpCheck(L);
    }
}
