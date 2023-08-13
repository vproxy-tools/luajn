package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.Helper;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;

public class Tutorial12 {
    public static void main(String[] args) {
        LuaJNative.fullInit();
        System.out.println("Tutorial.12 Get/Set Global Variables");

        try (var allocator = Allocator.ofPooled(); var L = new LuaJNState(allocator)) {
            LuaLib.get().openLibs(L.getLuaState());

            // language="lua"
            L.load("""
                a = 1
                b = "hello"
                                
                print("lua: a =", a)
                print("lua: b =", b)
                """);
            Helper.get().flushStdout();

            var _G = L.getGlobal();

            var a = _G.getLong("a");
            System.out.println("java: a = " + a);
            var b = _G.getString("b");
            System.out.println("java: b = " + b);

            System.out.println("setting values ...");
            _G.put("a", 2);
            _G.put("b", "world");

            // language="lua"
            L.load("""
                print("lua: a =", a)
                print("lua: b =", b)
                """);
            Helper.get().flushStdout();
        }
    }
}
