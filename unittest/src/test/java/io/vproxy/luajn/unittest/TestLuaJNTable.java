package io.vproxy.luajn.unittest;

import io.vproxy.luajn.LuaJNFunction;
import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.LuaJNTable;
import io.vproxy.luajn.LuaJNUserData;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.PNIString;
import org.junit.Test;

import java.lang.foreign.MemorySegment;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@SuppressWarnings({"RemoveUnusedLocal"})
public class TestLuaJNTable {
    @Test
    public void arrayLength() {
        // language="lua"
        var L = Utils.create("""
            ls = { 1, 2, 3, 4, 100, 200 }
            """);
        Utils.postOpCheck(L);
        assertEquals(0, L.getGlobal().arrayLength());

        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            assertEquals(6, ls.arrayLength());
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void putInteger() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            map.put("a", 1);
            Utils.postOpCheck(L);
            map.put("b", 2);
            Utils.postOpCheck(L);
            // language="lua"
            long res = L.loadLong("""
                return map.a + map.b
                """);
            Utils.postOpCheck(L);
            assertEquals(3, res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void setInteger() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            ls.set(1, 10);
            Utils.postOpCheck(L);
            ls.set(2, 20);
            Utils.postOpCheck(L);
            ls.set(3, 30);
            Utils.postOpCheck(L);
            // language="lua"
            long res = L.loadLong("""
                local total = 0
                for i, n in ipairs(ls) do
                  total = total + n
                end
                return total
                """);
            Utils.postOpCheck(L);
            assertEquals(60, res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getInteger() {
        // language="lua"
        var L = Utils.create("""
            map = {a = 1, b = 2}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            var a = map.getLong("a");
            Utils.postOpCheck(L);
            assertEquals(1L, a);
            var b = map.getLong("b");
            Utils.postOpCheck(L);
            assertEquals(2L, b);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getIntegerIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {3, 4}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            var a = ls.getLong(1);
            Utils.postOpCheck(L);
            assertEquals(3L, a);
            var b = ls.getLong(2);
            Utils.postOpCheck(L);
            assertEquals(4L, b);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void putNumber() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            map.put("a", 5.12);
            Utils.postOpCheck(L);
            map.put("b", 10.24);
            Utils.postOpCheck(L);
            // language="lua"
            double res = L.loadNumber("""
                return map.a + map.b
                """);
            Utils.postOpCheck(L);
            assertEquals(15.36, res, 0);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void setNumber() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            ls.set(1, 1.2);
            Utils.postOpCheck(L);
            ls.set(2, 2.3);
            Utils.postOpCheck(L);
            ls.set(3, 3.4);
            Utils.postOpCheck(L);
            // language="lua"
            double res = L.loadNumber("""
                local total = 0
                for i, n in ipairs(ls) do
                  total = total + n
                end
                return total
                """);
            Utils.postOpCheck(L);
            assertEquals(6.9, res, 0);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getNumber() {
        // language="lua"
        var L = Utils.create("""
            map = {a = 9.8, b = 7.6}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            var a = map.getNumber("a");
            Utils.postOpCheck(L);
            assertEquals(9.8, a, 0);
            var b = map.getNumber("b");
            Utils.postOpCheck(L);
            assertEquals(7.6, b, 0);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getNumberIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {5.4, 3.2}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            var a = ls.getNumber(1);
            Utils.postOpCheck(L);
            assertEquals(5.4, a, 0);
            var b = ls.getNumber(2);
            Utils.postOpCheck(L);
            assertEquals(3.2, b, 0);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isNumber() {
        // language="lua"
        var L = Utils.create("""
            map = {a = 9.8, b = "abc", c = 7.6}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            var a = map.isNumber("a");
            Utils.postOpCheck(L);
            assertTrue(a);
            var b = map.isNumber("b");
            Utils.postOpCheck(L);
            assertFalse(b);
            var c = map.isNumber("c");
            Utils.postOpCheck(L);
            assertTrue(c);
        }
    }

    @Test
    public void isNumberIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {5.4, "abc", 3.2}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            var a = ls.isNumber(1);
            Utils.postOpCheck(L);
            assertTrue(a);
            var b = ls.isNumber(2);
            Utils.postOpCheck(L);
            assertFalse(b);
            var c = ls.isNumber(3);
            Utils.postOpCheck(L);
            assertTrue(c);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void putString() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            map.put("a", "xx");
            Utils.postOpCheck(L);
            map.put("b", "yy");
            Utils.postOpCheck(L);
            // language="lua"
            var res = L.loadString("""
                return map.a .. map.b
                """);
            Utils.postOpCheck(L);
            assertEquals("xxyy", res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void setString() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            ls.set(1, "mm");
            Utils.postOpCheck(L);
            ls.set(2, "nn");
            Utils.postOpCheck(L);
            ls.set(3, "oo");
            Utils.postOpCheck(L);
            // language="lua"
            var res = L.loadString("""
                local res = ""
                for i, s in ipairs(ls) do
                    res = res .. s
                end
                return res
                """);
            Utils.postOpCheck(L);
            assertEquals("mmnnoo", res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getString() {
        // language="lua"
        var L = Utils.create("""
            map = {a = "xx", b = "yy"}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            var a = map.getString("a");
            Utils.postOpCheck(L);
            assertEquals("xx", a);
            var b = map.getString("b");
            Utils.postOpCheck(L);
            assertEquals("yy", b);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getStringIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {"aa", "bb"}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            var a = ls.getString(1);
            Utils.postOpCheck(L);
            assertEquals("aa", a);
            var b = ls.getString(2);
            Utils.postOpCheck(L);
            assertEquals("bb", b);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isString() {
        // language="lua"
        var L = Utils.create("""
            map = {a = "xx", b = 123, c = "yy"}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            var a = map.isString("a");
            Utils.postOpCheck(L);
            assertTrue(a);
            var b = map.isString("b");
            Utils.postOpCheck(L);
            assertFalse(b);
            var c = map.isString("c");
            Utils.postOpCheck(L);
            assertTrue(c);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isStringIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {"aa", 123, "bb"}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            var a = ls.isString(1);
            Utils.postOpCheck(L);
            assertTrue(a);
            var b = ls.isString(2);
            Utils.postOpCheck(L);
            assertFalse(b);
            var c = ls.isString(3);
            Utils.postOpCheck(L);
            assertTrue(c);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void remove() {
        // language="lua"
        var L = Utils.create("""
            map = {a = "xx", b = "yy"}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            map.remove("a");
            Utils.postOpCheck(L);
            // language="lua"
            var res = L.loadString("""
                local res = ""
                for k, v in pairs(map) do
                    res = res .. v
                end
                return res
                """);
            Utils.postOpCheck(L);
            assertEquals("yy", res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void removeIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {"mm", "nn", "oo"}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            ls.remove(3);
            Utils.postOpCheck(L);
            // language="lua"
            var res = L.loadString("""
                local res = ""
                for i, s in ipairs(ls) do
                    res = res .. s
                end
                return res
                """);
            Utils.postOpCheck(L);
            assertEquals("mmnn", res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isNil() {
        // language="lua"
        var L = Utils.create("""
            map = {a = nil, c = "a"}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            var a = map.isNil("a");
            Utils.postOpCheck(L);
            assertTrue(a);
            var b = map.isNil("b");
            Utils.postOpCheck(L);
            assertTrue(b);
            var c = map.isNil("c");
            Utils.postOpCheck(L);
            assertFalse(c);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isNilIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {nil, "abc", nil}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            var a = ls.isNil(1);
            Utils.postOpCheck(L);
            assertTrue(a);
            var b = ls.isNil(2);
            Utils.postOpCheck(L);
            assertFalse(b);
            var c = ls.isNil(3);
            Utils.postOpCheck(L);
            assertTrue(c);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void putBool() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            map.put("a", true);
            Utils.postOpCheck(L);
            map.put("b", false);
            Utils.postOpCheck(L);
            // language="lua"
            var res = L.loadBoolean("""
                return map.a
                """);
            Utils.postOpCheck(L);
            assertTrue(res);
            // language="lua"
            res = L.loadBoolean("""
                return map.b
                """);
            Utils.postOpCheck(L);
            assertFalse(res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void setBool() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            ls.set(1, true);
            Utils.postOpCheck(L);
            ls.set(2, false);
            Utils.postOpCheck(L);
            ls.set(3, true);
            Utils.postOpCheck(L);
            // language="lua"
            var res = L.loadString("""
                local res = ""
                for i, b in ipairs(ls) do
                    res = res .. tostring(b)
                end
                return res
                """);
            Utils.postOpCheck(L);
            assertEquals("truefalsetrue", res);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getBool() {
        // language="lua"
        var L = Utils.create("""
            map = {a = true, b = false}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            var a = map.getBoolean("a");
            Utils.postOpCheck(L);
            assertTrue(a);
            var b = map.getBoolean("b");
            Utils.postOpCheck(L);
            assertFalse(b);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getBoolIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {true, false}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            var a = ls.getBoolean(1);
            Utils.postOpCheck(L);
            assertTrue(a);
            var b = ls.getBoolean(2);
            Utils.postOpCheck(L);
            assertFalse(b);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isBool() {
        // language="lua"
        var L = Utils.create("""
            map = {a = true, b = 123, c = false}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            var a = map.isBoolean("a");
            Utils.postOpCheck(L);
            assertTrue(a);
            var b = map.isBoolean("b");
            Utils.postOpCheck(L);
            assertFalse(b);
            var c = map.isBoolean("c");
            Utils.postOpCheck(L);
            assertTrue(c);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isBoolIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {true, 123, false}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            var a = ls.isBoolean(1);
            Utils.postOpCheck(L);
            assertTrue(a);
            var b = ls.isBoolean(2);
            Utils.postOpCheck(L);
            assertFalse(b);
            var c = ls.isBoolean(3);
            Utils.postOpCheck(L);
            assertTrue(c);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void putLightUserData() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map"); var allocator = Allocator.ofConfined()) {
            Utils.postOpCheck(L);
            map.put("a", new PNIString(allocator, "hello").MEMORY);
            Utils.postOpCheck(L);
            map.put("b", new PNIString(allocator, "world").MEMORY);
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                a = map.a
                """);
            Utils.postOpCheck(L);
            assertEquals("hello", L.getGlobal().getLightUserData("a").reinterpret(10).getUtf8String(0));
            // language="lua"
            L.load("""
                b = map.b
                """);
            Utils.postOpCheck(L);
            assertEquals("world", L.getGlobal().getLightUserData("b").reinterpret(10).getUtf8String(0));
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void setLightUserData() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls"); var allocator = Allocator.ofConfined()) {
            Utils.postOpCheck(L);
            ls.set(1, new PNIString(allocator, "hello").MEMORY);
            Utils.postOpCheck(L);
            ls.set(2, new PNIString(allocator, "world").MEMORY);
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                a = ls[1]
                """);
            Utils.postOpCheck(L);
            assertEquals("hello", L.getGlobal().getLightUserData("a").reinterpret(10).getUtf8String(0));
            // language="lua"
            L.load("""
                b = ls[2]
                """);
            Utils.postOpCheck(L);
            assertEquals("world", L.getGlobal().getLightUserData("b").reinterpret(10).getUtf8String(0));
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getLightUserData() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map"); var allocator = Allocator.ofConfined()) {
            Utils.postOpCheck(L);
            var str = new PNIString(allocator, "hello");
            map.put("a", str.MEMORY);
            Utils.postOpCheck(L);
            var a = map.getLightUserData("a");
            Utils.postOpCheck(L);
            assertEquals(str.MEMORY.address(), a.address());
            assertEquals("hello", a.reinterpret(10).getUtf8String(0));
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getLightUserDataIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls"); var allocator = Allocator.ofConfined()) {
            Utils.postOpCheck(L);
            var str = new PNIString(allocator, "hello");
            ls.set(1, str.MEMORY);
            Utils.postOpCheck(L);
            var a = ls.getLightUserData(1);
            Utils.postOpCheck(L);
            assertEquals(str.MEMORY.address(), a.address());
            assertEquals("hello", a.reinterpret(10).getUtf8String(0));
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isLightUserData() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map"); var allocator = Allocator.ofConfined()) {
            Utils.postOpCheck(L);
            var str = new PNIString(allocator, "hello");
            map.put("a", str.MEMORY);
            Utils.postOpCheck(L);
            assertTrue(map.isLightUserData("a"));
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isLightUserDataIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls"); var allocator = Allocator.ofConfined()) {
            Utils.postOpCheck(L);
            var str = new PNIString(allocator, "hello");
            ls.set(1, str.MEMORY);
            Utils.postOpCheck(L);
            assertTrue(ls.isLightUserData(1));
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void putUserData() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map"); var data = L.newUserData(10)) {
            Utils.postOpCheck(L);
            data.MEMORY.setUtf8String(0, "hello");
            map.put("a", data);
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                a = map.a
                """);
            Utils.postOpCheck(L);
            try (var a = L.getGlobal().getUserData("a")) {
                Utils.postOpCheck(L);
                assertEquals("hello", a.MEMORY.reinterpret(10).getUtf8String(0));
                assertEquals(data.MEMORY.address(), a.MEMORY.address());
                assertEquals(data.MEMORY.address(), L.getGlobal().getUserDataMemory("a").address());
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void setUserData() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls"); var data = L.newUserData(10)) {
            Utils.postOpCheck(L);
            data.MEMORY.setUtf8String(0, "hello");
            ls.set(1, data);
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                a = ls[1]
                """);
            Utils.postOpCheck(L);
            try (var a = L.getGlobal().getUserData("a")) {
                Utils.postOpCheck(L);
                assertEquals("hello", a.MEMORY.reinterpret(10).getUtf8String(0));
                assertEquals(data.MEMORY.address(), a.MEMORY.address());
                assertEquals(data.MEMORY.address(), L.getGlobal().getUserDataMemory("a").address());
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getUserData() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map"); var data = L.newUserData(10)) {
            Utils.postOpCheck(L);
            data.MEMORY.setUtf8String(0, "hello");
            map.put("a", data);
            Utils.postOpCheck(L);
            var a = map.getUserData("a");
            Utils.postOpCheck(L);
            assertEquals(data.MEMORY.address(), a.MEMORY.address());
            assertEquals("hello", a.MEMORY.reinterpret(10).getUtf8String(0));
            var mem = map.getUserDataMemory("a");
            Utils.postOpCheck(L);
            assertEquals(data.MEMORY.address(), mem.address());
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getUserDataIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls"); var data = L.newUserData(10)) {
            Utils.postOpCheck(L);
            data.MEMORY.setUtf8String(0, "hello");
            ls.set(1, data);
            Utils.postOpCheck(L);
            var a = ls.getUserData(1);
            Utils.postOpCheck(L);
            assertEquals(data.MEMORY.address(), a.MEMORY.address());
            assertEquals("hello", a.MEMORY.reinterpret(10).getUtf8String(0));
            var mem = ls.getUserDataMemory(1);
            Utils.postOpCheck(L);
            assertEquals(data.MEMORY.address(), mem.address());
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isUserData() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map"); var data = L.newUserData(10)) {
            Utils.postOpCheck(L);
            map.put("a", data);
            Utils.postOpCheck(L);
            assertTrue(map.isUserData("a"));
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isUserDataIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls"); var data = L.newUserData(10)) {
            Utils.postOpCheck(L);
            ls.set(1, data);
            Utils.postOpCheck(L);
            assertTrue(ls.isUserData(1));
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void putCallSite() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            map.put("a", LL -> {
                LL.pushInteger(10);
                return 1;
            });
            Utils.postOpCheck(L);
            map.put("b", LL -> {
                LL.pushInteger(20);
                return 1;
            });
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                a = map.a()
                b = map.b()
                """);
            Utils.postOpCheck(L);
            var a = L.getGlobal().getLong("a");
            Utils.postOpCheck(L);
            var b = L.getGlobal().getLong("b");
            Utils.postOpCheck(L);
            assertEquals(10, a);
            assertEquals(20, b);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void setCallSite() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            ls.set(1, LL -> {
                LL.pushInteger(30);
                return 1;
            });
            Utils.postOpCheck(L);
            ls.set(2, LL -> {
                LL.pushInteger(40);
                return 1;
            });
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                a = ls[1]()
                b = ls[2]()
                """);
            Utils.postOpCheck(L);
            var a = L.getGlobal().getLong("a");
            Utils.postOpCheck(L);
            var b = L.getGlobal().getLong("b");
            Utils.postOpCheck(L);
            assertEquals(30, a);
            assertEquals(40, b);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void putPNIFunc() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);

            map.put("a", PNIFunc.VoidFunc.of(v -> 123));
            Utils.postOpCheck(L);
            map.put("b", PNIFunc.VoidFunc.of(v -> 456));
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                local luajn = require("luajn")
                a = luajn.upcall(map.a, nil)
                b = luajn.upcall(map.b, nil)
                """);
            Utils.postOpCheck(L);
            var a = L.getGlobal().getLong("a");
            Utils.postOpCheck(L);
            var b = L.getGlobal().getLong("b");
            Utils.postOpCheck(L);
            assertEquals(123, a);
            assertEquals(456, b);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void setPNIFunc() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            ls.set(1, PNIFunc.VoidFunc.of(v -> 123));
            Utils.postOpCheck(L);
            ls.set(2, PNIFunc.VoidFunc.of(v -> 456));
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                local luajn = require("luajn")
                a = luajn.upcall(ls[1], nil)
                b = luajn.upcall(ls[2], nil)
                """);
            Utils.postOpCheck(L);
            var a = L.getGlobal().getLong("a");
            Utils.postOpCheck(L);
            var b = L.getGlobal().getLong("b");
            Utils.postOpCheck(L);
            assertEquals(123, a);
            assertEquals(456, b);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void putPNIRef() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);

            map.put("a", PNIRef.Func.<List<Integer>>of(ls -> {
                ls.add(123);
                return 0;
            }));
            Utils.postOpCheck(L);
            var ls = new ArrayList<Integer>();
            map.put("b", PNIRef.of(ls));
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                local luajn = require("luajn")
                a = luajn.upcall(map.a, map.b)
                """);
            Utils.postOpCheck(L);
            var a = L.getGlobal().getLong("a");
            Utils.postOpCheck(L);
            assertEquals(0, a);
            assertEquals(List.of(123), ls);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void setPNIRef() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            ls.set(1, PNIRef.Func.<List<Integer>>of(ll -> {
                ll.add(123);
                return 0;
            }));
            Utils.postOpCheck(L);
            var ll = new ArrayList<Integer>();
            ls.set(2, PNIRef.of(ll));
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                local luajn = require("luajn")
                a = luajn.upcall(ls[1], ls[2])
                """);
            Utils.postOpCheck(L);
            var a = L.getGlobal().getLong("a");
            Utils.postOpCheck(L);
            assertEquals(0, a);
            assertEquals(List.of(123), ll);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void putTableAndGetTable() {
        // language="lua"
        var L = Utils.create("""
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            try (var table = L.newTable()) {
                Utils.postOpCheck(L);
                map.put("a", table);
                Utils.postOpCheck(L);
            }
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                map.a.x = 1122
                """);
            Utils.postOpCheck(L);
            try (var aTable = map.getTable("a")) {
                Utils.postOpCheck(L);
                var x = aTable.getLong("x");
                Utils.postOpCheck(L);
                assertEquals(1122, x);
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void setTableAndGetTableIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            try (var table = L.newTable()) {
                Utils.postOpCheck(L);
                ls.set(1, table);
                Utils.postOpCheck(L);
            }
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                ls[1].y = 2233
                """);
            Utils.postOpCheck(L);
            try (var aTable = ls.getTable(1)) {
                Utils.postOpCheck(L);
                var x = aTable.getLong("y");
                Utils.postOpCheck(L);
                assertEquals(2233, x);
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isTable() {
        // language="lua"
        var L = Utils.create("""
            map = {a = {}, b = 123, c = {}}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            assertTrue(map.isTable("a"));
            Utils.postOpCheck(L);
            assertFalse(map.isTable("b"));
            Utils.postOpCheck(L);
            assertTrue(map.isTable("c"));
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isTableIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = {{}, 123, {}}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            assertTrue(ls.isTable(1));
            Utils.postOpCheck(L);
            assertFalse(ls.isTable(2));
            Utils.postOpCheck(L);
            assertTrue(ls.isTable(3));
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getFunction() {
        // language="lua"
        var L = Utils.create("""
            local fn = function(x)
                return x + 2
            end
            local fn2 = function(x)
                return x, x + 2
            end
            map = {fn = fn, fn2 = fn2}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            try (var fn = map.getFunction("fn")) {
                Utils.postOpCheck(L);
                int res = ((Number) fn.invoke(3)).intValue();
                Utils.postOpCheck(L);
                assertEquals(5, res);
            }
            Utils.postOpCheck(L);
            try (var fn = map.getFunction("fn2")) {
                Utils.postOpCheck(L);
                Object[] res = (Object[]) fn.invoke(4);
                Utils.postOpCheck(L);
                assertEquals(4, ((Number) res[0]).intValue());
                assertEquals(6, ((Number) res[1]).intValue());
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getFunctionIndex() {
        // language="lua"
        var L = Utils.create("""
            local fn = function(x)
                return x + 3
            end
            local fn2 = function(x)
                return x, x + 3
            end
            ls = {fn, fn2}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            try (var fn = ls.getFunction(1)) {
                Utils.postOpCheck(L);
                int res = ((Number) fn.invoke(11)).intValue();
                Utils.postOpCheck(L);
                assertEquals(14, res);
            }
            Utils.postOpCheck(L);
            try (var fn = ls.getFunction(2)) {
                Utils.postOpCheck(L);
                Object[] res = (Object[]) fn.invoke(12);
                Utils.postOpCheck(L);
                assertEquals(12, ((Number) res[0]).intValue());
                assertEquals(15, ((Number) res[1]).intValue());
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isFunction() {
        // language="lua"
        var L = Utils.create("""
            local fn = function(x)
                return x + 2
            end
            local fn2 = function(x)
                return x, x + 2
            end
            map = {fn = fn, fn2 = 123, fn3 = fn2}
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            assertTrue(map.isFunction("fn"));
            Utils.postOpCheck(L);
            assertFalse(map.isFunction("fn2"));
            Utils.postOpCheck(L);
            assertTrue(map.isFunction("fn3"));
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isFunctionIndex() {
        // language="lua"
        var L = Utils.create("""
            local fn = function(x)
                return x + 3
            end
            local fn2 = function(x)
                return x, x + 3
            end
            ls = {fn, 123, fn2}
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            assertTrue(ls.isFunction(1));
            Utils.postOpCheck(L);
            assertFalse(ls.isFunction(2));
            Utils.postOpCheck(L);
            assertTrue(ls.isFunction(3));
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void putCoroutine() {
        // language="lua"
        var L = Utils.create("""
            function func()
                coroutine.yield(1)
            end
            map = {}
            """);
        try (var map = L.getGlobal().getTable("map"); var allocator = Allocator.ofConfined(); var co = L.newCoroutine(allocator)) {
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
            map.put("a", co);
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                a = map.a
                """);
            Utils.postOpCheck(L);
            try (var a = L.getGlobal().getCoroutine("a", allocator); var func = L.getGlobal().getFunction("func")) {
                assertEquals(0, co.getLuaState().getTop());
                Utils.postOpCheck(L);
                var res = (Number) func.invoke(a);
                Utils.postOpCheck(L);
                Utils.postOpCheck(a);
                assertEquals(1, res.intValue());
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void setCoroutine() {
        // language="lua"
        var L = Utils.create("""
            function func()
                coroutine.yield(1)
            end
            ls = {}
            """);
        try (var ls = L.getGlobal().getTable("ls"); var allocator = Allocator.ofConfined(); var co = L.newCoroutine(allocator)) {
            Utils.postOpCheck(L);
            Utils.postOpCheck(co);
            ls.set(1, co);
            Utils.postOpCheck(L);
            // language="lua"
            L.load("""
                a = ls[1]
                """);
            Utils.postOpCheck(L);
            try (var a = L.getGlobal().getCoroutine("a", allocator); var func = L.getGlobal().getFunction("func")) {
                Utils.postOpCheck(L);
                Utils.postOpCheck(a);
                var res = (Number) func.invoke(a);
                Utils.postOpCheck(L);
                Utils.postOpCheck(a);
                assertEquals(1, res.intValue());
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getCoroutine() {
        // language="lua"
        var L = Utils.create("""
            map = {
                a = coroutine.create(function() coroutine.yield(1) end)
            }
            """);
        try (var allocator = Allocator.ofConfined(); var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            try (var a = map.getCoroutine("a", allocator)) {
                Utils.postOpCheck(L);
                Utils.postOpCheck(a, 1);
                var res = (Number) a.resume();
                Utils.postOpCheck(L);
                Utils.postOpCheck(a);
                assertEquals(1, res.intValue());

                try (var aa = map.getCoroutine("a", allocator)) {
                    Utils.postOpCheck(L);
                    Utils.postOpCheck(aa);
                    assertNotEquals(a.getLuaState().MEMORY.address(), aa.getLuaState().MEMORY.address());
                    assertEquals(a.getLuaState().getL().address(), aa.getLuaState().getL().address());
                }
                Utils.postOpCheck(L);
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getCoroutineIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = { coroutine.create(function() coroutine.yield(1) end) }
            """);
        try (var allocator = Allocator.ofConfined(); var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            try (var a = ls.getCoroutine(1, allocator)) {
                Utils.postOpCheck(L);
                Utils.postOpCheck(a, 1);
                var res = (Number) a.resume();
                Utils.postOpCheck(L);
                Utils.postOpCheck(a);
                assertEquals(1, res.intValue());

                try (var aa = ls.getCoroutine(1, allocator)) {
                    Utils.postOpCheck(L);
                    Utils.postOpCheck(aa);
                    assertNotEquals(a.getLuaState().MEMORY.address(), aa.getLuaState().MEMORY.address());
                    assertEquals(a.getLuaState().getL().address(), aa.getLuaState().getL().address());
                }
                Utils.postOpCheck(L);
            }
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isCoroutine() {
        // language="lua"
        var L = Utils.create("""
            map = {
                a = coroutine.create(function() coroutine.yield(1) end)
            }
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            assertTrue(map.isCoroutine("a"));
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void isCoroutineIndex() {
        // language="lua"
        var L = Utils.create("""
            ls = { coroutine.create(function() coroutine.yield(1) end) }
            """);
        try (var ls = L.getGlobal().getTable("ls")) {
            Utils.postOpCheck(L);
            assertTrue(ls.isCoroutine(1));
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void forEach() {
        // language="lua"
        var L = Utils.create("""
            map = {
              a = 1,
              b = 3.2,
              c = "abc",
              d = nil,
              e = {a = 1, b = 2},
              f = function() return 1 end,
              g = true,
              h = false,
            }
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            int[] count = {0};
            map.forEach((k, v) -> {
                count[0]++;
                switch (k) {
                    case "a" -> {
                        assertEquals(1, ((Number) v).intValue());
                        return;
                    }
                    case "b" -> {
                        assertEquals(3.2, ((Number) v).doubleValue(), 0);
                        return;
                    }
                    case "c" -> {
                        assertEquals("abc", v);
                        return;
                    }
                    case "e" -> {
                        assertTrue(v instanceof LuaJNTable);
                        var t = (LuaJNTable) v;
                        assertEquals(1, t.getLong("a"));
                        assertEquals(2, t.getLong("b"));
                        return;
                    }
                    case "f" -> {
                        assertTrue(v instanceof LuaJNFunction);
                        var f = (LuaJNFunction) v;
                        assertEquals(1, ((Number) f.invoke()).intValue());
                        return;
                    }
                    case "g" -> {
                        assertTrue((boolean) v);
                        return;
                    }
                    case "h" -> {
                        assertFalse((boolean) v);
                        return;
                    }
                }
                fail();
            });
            Utils.postOpCheck(L);
            assertEquals(7, count[0]);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void forEachWithAllocator() {
        // language="lua"
        var L = Utils.create("""
            map = {
              a = nil,
              b = nil,
              c = coroutine.create(function() coroutine.yield(1) end)
            }
            """);
        try (var map = L.getGlobal().getTable("map"); var allocator = Allocator.ofConfined(); var bUd = L.newUserData(10);) {
            Utils.postOpCheck(L);
            var aMem = allocator.allocate(10);
            aMem.setUtf8String(0, "hello");
            bUd.MEMORY.setUtf8String(0, "world");

            map.put("a", aMem);
            Utils.postOpCheck(L);
            map.put("b", bUd);
            Utils.postOpCheck(L);

            int[] count = {0};
            map.forEach(allocator, (k, v) -> {
                ++count[0];
                switch (k) {
                    case "a" -> {
                        var m = (MemorySegment) v;
                        assertEquals("hello", m.reinterpret(10).getUtf8String(0));
                        return;
                    }
                    case "b" -> {
                        var ud = (LuaJNUserData) v;
                        assertEquals("world", ud.MEMORY.reinterpret(10).getUtf8String(0));
                        return;
                    }
                    case "c" -> {
                        var co = (LuaJNState) v;
                        var res = (Number) co.resume();
                        assertEquals(1, res.intValue());
                        return;
                    }
                }
                fail();
            });
            Utils.postOpCheck(L);
            assertEquals(3, count[0]);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void foreach() {
        // language="lua"
        var L = Utils.create("""
            map = {
              a = 1,
              b = 2,
              c = 3,
              d = 4,
              e = 5,
            }
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            int[] count = {0};
            map.foreach((k, v) -> {
                ++count[0];
                switch (k) {
                    case "a" -> {
                        assertEquals(1, ((Number) v).intValue());
                        return true;
                    }
                    case "b" -> {
                        assertEquals(2, ((Number) v).intValue());
                        return false;
                    }
                    case "c" -> {
                        assertEquals(3, ((Number) v).intValue());
                        return true;
                    }
                    case "d" -> {
                        assertEquals(4, ((Number) v).intValue());
                        return false;
                    }
                    case "e" -> {
                        assertEquals(5, ((Number) v).intValue());
                        return true;
                    }
                }
                fail();
                return true;
            });
            Utils.postOpCheck(L);
            assertTrue(count[0] <= 4);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void foreachWithAllocator() {
        // language="lua"
        var L = Utils.create("""
            map = {
              a = 1,
              b = 2,
              c = 3,
              d = 4,
              e = 5,
            }
            """);
        try (var map = L.getGlobal().getTable("map"); var allocator = Allocator.ofConfined()) {
            Utils.postOpCheck(L);
            int[] count = {0};
            map.foreach(allocator, (k, v) -> {
                ++count[0];
                switch (k) {
                    case "a" -> {
                        assertEquals(1, ((Number) v).intValue());
                        return true;
                    }
                    case "b" -> {
                        assertEquals(2, ((Number) v).intValue());
                        return false;
                    }
                    case "c" -> {
                        assertEquals(3, ((Number) v).intValue());
                        return true;
                    }
                    case "d" -> {
                        assertEquals(4, ((Number) v).intValue());
                        return false;
                    }
                    case "e" -> {
                        assertEquals(5, ((Number) v).intValue());
                        return true;
                    }
                }
                fail();
                return true;
            });
            Utils.postOpCheck(L);
            assertTrue(count[0] <= 4);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void get() {
        // language="lua"
        var L = Utils.create("""
            map = {
              a = 1,
              b = "hello",
              c = coroutine.create(function() coroutine.yield(1) end)
            }
            """);
        try (var map = L.getGlobal().getTable("map"); var allocator = Allocator.ofConfined()) {
            Utils.postOpCheck(L);
            assertEquals(1, ((Number) map.get("a")).intValue());
            assertEquals("hello", map.get("b"));
            var c = (LuaJNState) map.get("c", allocator);
            Utils.postOpCheck(L);
            assertEquals(1, ((Number) c.resume()).intValue());
            Utils.postOpCheck(L);
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void getIndex() {
        // language="lua"
        var L = Utils.create("""
            map = {
              1, "hello", coroutine.create(function() coroutine.yield(1) end)
            }
            """);
        try (var map = L.getGlobal().getTable("map"); var allocator = Allocator.ofConfined()) {
            Utils.postOpCheck(L);
            assertEquals(1, ((Number) map.get(1)).intValue());
            Utils.postOpCheck(L);
            assertEquals("hello", map.get(2));
            Utils.postOpCheck(L);
            var c = (LuaJNState) map.get(3, allocator);
            Utils.postOpCheck(L);
            assertEquals(1, ((Number) c.resume()).intValue());
        }
        Utils.postOpCheck(L);
    }

    @Test
    public void keys() {
        // language="lua"
        var L = Utils.create("""
            map = {
              a = 1,
              b = 2,
              c = 3,
              d = 4,
            }
            """);
        try (var map = L.getGlobal().getTable("map")) {
            Utils.postOpCheck(L);
            var keySet = map.keySet();
            Utils.postOpCheck(L);
            assertEquals(Set.of("a", "b", "c", "d"), keySet);
        }
        Utils.postOpCheck(L);
    }
}
