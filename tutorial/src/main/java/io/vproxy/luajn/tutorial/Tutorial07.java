package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.Helper;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.luajn.n.LuaState;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIString;

public class Tutorial07 {
    public static void main(String[] args) throws Exception {
        LuaJNative.fullInit();
        System.out.println("Tutorial 07. Call Java Function from Lua: splitlib");

        // language="lua"
        var lua = """
            local s = require "splitlib"
                            
            local t = s.split("hi,ho,there", ",");
                            
            for i, v in pairs(t) do
                print(i, v)
            end
            """;

        try (var allocator = Allocator.ofConfined()) {
            var L = Lua.get().newState(allocator);
            LuaLib.get().openLibs(L);

            Lua.get().registerModule(L, new PNIString(allocator, "splitlib"));

            L.pushString(new PNIString(allocator, "split"));
            L.pushFunction(Tutorial07::split);
            L.setTable(-3);

            L.pop(1);

            Lua.get().loadString(L, new PNIString(allocator, lua));
            int err = L.pcall(0, 0, 0);
            if (err != 0) {
                System.out.println("pcall() failed. " + L.toString(-1));
                return;
            }
            Helper.get().flushStdout();

            L.close();
        }
    }

    private static int split(LuaState L) {
        var s = L.toString(1).toString();
        var sep = L.toString(2).toString();

        var split = s.split(sep);

        L.newTable();

        try (var allocator = Allocator.ofConfined()) {
            for (int i = 0; i < split.length; i++) {
                var str = split[i];
                L.pushString(new PNIString(allocator, str));
                L.rawSetI(-2, i + 1);
            }
        }

        return 1;
    }
}
