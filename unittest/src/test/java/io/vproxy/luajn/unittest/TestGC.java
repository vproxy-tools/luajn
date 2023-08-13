package io.vproxy.luajn.unittest;

import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.n.LuaState;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.CallSite;
import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIString;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGC {
    @Test
    public void global() {
        var storageSize = PNIFunc.currentFuncStorageSize();
        try (var allocator = Allocator.ofConfined(); var L = new LuaJNState(allocator)) {
            Utils.postOpCheck(L);

            var _G = L.getGlobal();
            _G.put("a", LL -> 0);
            Utils.postOpCheck(L);

            assertEquals(storageSize + 1, PNIFunc.currentFuncStorageSize());
        }
        assertEquals(storageSize, PNIFunc.currentFuncStorageSize());
    }

    @Test
    public void func() {
        var storageSize = PNIFunc.currentFuncStorageSize();
        try (var allocator = Allocator.ofConfined(); var L = new LuaJNState(allocator)) {
            Utils.postOpCheck(L);

            // language="lua"
            L.load("""
                local holder = nil
                function f(ff)
                    holder = ff -- ensure it won't be released after the function call
                    return ff()
                end
                """);
            Utils.postOpCheck(L);

            var _G = L.getGlobal();
            try (var f = _G.getFunction("f")) {
                //noinspection Convert2Lambda
                var s = (String) f.invoke(new CallSite<LuaState>() {
                    @Override
                    public int call(LuaState L) {
                        PNIString.temporary("hello", L::pushString);
                        return 1;
                    }
                });
                Utils.postOpCheck(L);
                assertEquals("hello", s);
            }
            Utils.postOpCheck(L);

            assertEquals(storageSize + 1, PNIFunc.currentFuncStorageSize());
        }
        assertEquals(storageSize, PNIFunc.currentFuncStorageSize());
    }

    @Test
    public void globalPNIFunc() {
        var storageSize = PNIFunc.currentFuncStorageSize();
        try (var allocator = Allocator.ofConfined(); var L = new LuaJNState(allocator)) {
            Utils.postOpCheck(L);

            var _G = L.getGlobal();
            _G.put("a", PNIFunc.VoidFunc.of(v -> 0));
            Utils.postOpCheck(L);

            assertEquals(storageSize + 1, PNIFunc.currentFuncStorageSize());
        }
        assertEquals(storageSize, PNIFunc.currentFuncStorageSize());
    }

    @Test
    public void funcPNIFunc() {
        var storageSize = PNIFunc.currentFuncStorageSize();
        try (var allocator = Allocator.ofConfined(); var L = new LuaJNState(allocator)) {
            Utils.postOpCheck(L);

            // language="lua"
            L.load("""
                local holder = nil
                function f(ff)
                    holder = ff -- ensure it won't be released after the function call
                end
                """);
            Utils.postOpCheck(L);

            var _G = L.getGlobal();
            try (var f = _G.getFunction("f")) {
                f.invoke(PNIFunc.VoidFunc.of(v -> 0));
                Utils.postOpCheck(L);
            }
            Utils.postOpCheck(L);

            assertEquals(storageSize + 1, PNIFunc.currentFuncStorageSize());
        }
        assertEquals(storageSize, PNIFunc.currentFuncStorageSize());
    }
}
