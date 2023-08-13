package io.vproxy.luajn.unittest;

import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings({"RemoveUnusedLocal", "resource"})
public class TestLuaJNState {
    @Test
    public void load() {
        try (var allocator = Allocator.ofConfined(); var L = new LuaJNState(allocator)) {
            Utils.postOpCheck(L);
            L.load("a = 1");
            Utils.postOpCheck(L);
            assertEquals(1L, L.getGlobal().getLong("a"));
            Utils.postOpCheck(L);
        }
    }

    @Test
    public void loadLong() {
        try (var allocator = Allocator.ofConfined(); var L = new LuaJNState(allocator)) {
            Utils.postOpCheck(L);
            var n = L.loadLong("return 1");
            Utils.postOpCheck(L);
            assertEquals(1L, n);
        }
    }

    @Test
    public void loadNumber() {
        try (var allocator = Allocator.ofConfined(); var L = new LuaJNState(allocator)) {
            Utils.postOpCheck(L);
            var n = L.loadNumber("return 3.2");
            Utils.postOpCheck(L);
            assertEquals(3.2, n, 0);
        }
    }

    @Test
    public void loadString() {
        try (var allocator = Allocator.ofConfined(); var L = new LuaJNState(allocator)) {
            Utils.postOpCheck(L);
            var s = L.loadString("return \"hello\"");
            Utils.postOpCheck(L);
            assertEquals("hello", s);
        }
    }

    @Test
    public void loadBool() {
        try (var allocator = Allocator.ofConfined(); var L = new LuaJNState(allocator)) {
            Utils.postOpCheck(L);
            var b = L.loadBoolean("return true");
            Utils.postOpCheck(L);
            assertTrue(b);
            b = L.loadBoolean("return false");
            Utils.postOpCheck(L);
            assertFalse(b);
        }
    }

    @Test
    public void loadTable() {
        try (var allocator = Allocator.ofConfined(); var L = new LuaJNState(allocator)) {
            Utils.postOpCheck(L);
            var t = L.loadTable("return {a = 1, b = 2}");
            Utils.postOpCheck(L);
            try (t) {
                assertEquals(1L, t.getLong("a"));
                Utils.postOpCheck(L);
                assertEquals(2L, t.getLong("b"));
                Utils.postOpCheck(L);
            }
            Utils.postOpCheck(L);
        }
    }

    @Test
    public void loadFunction() {
        try (var allocator = Allocator.ofConfined(); var L = new LuaJNState(allocator)) {
            Utils.postOpCheck(L);
            var f = L.loadFunction("return function() return 1 end");
            Utils.postOpCheck(L);
            try (f) {
                assertEquals(1, ((Number) f.invoke()).intValue());
                Utils.postOpCheck(L);
            }
            Utils.postOpCheck(L);
        }
    }

    @Test
    public void loadCoroutine() {
        try (var allocator = Allocator.ofConfined(); var L = new LuaJNState(allocator)) {
            Utils.postOpCheck(L);
            LuaLib.get().openLibs(L.getLuaState());
            Utils.postOpCheck(L);

            var co = L.loadCoroutine(allocator, "local coroutine = require(\"coroutine\")\n" +
                                                "return coroutine.create(function() coroutine.yield(1) end)");
            Utils.postOpCheck(L);
            Utils.postOpCheck(co, 1);
            try (co) {
                assertEquals(1, ((Number) co.resume()).intValue());
                Utils.postOpCheck(L);
                Utils.postOpCheck(co);
            }
            Utils.postOpCheck(L);
        }
    }

    @Test
    public void registry() {
        var L = Utils.create("");
        L.getRegistry().put("xx", "yy");
        Utils.postOpCheck(L);
        assertEquals("yy", L.getRegistry().getString("xx"));
        Utils.postOpCheck(L);
    }
}
