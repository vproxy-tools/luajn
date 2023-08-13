package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.Helper;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.luajn.n.LuaState;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIString;

public class Tutorial06 {
    public static void main(String[] args) throws Exception {
        LuaJNative.fullInit();
        System.out.println("Tutorial 06. Call Java Function from Lua: power");

        // language="lua"
        var lua = """
            local power = require("power")
            print(power.square(2.5))
            print(power.cube(2.5))
            """;

        try (var allocator = Allocator.ofConfined()) {
            var L = Lua.get().newState(allocator);
            LuaLib.get().openLibs(L);

            Lua.get().registerModule(L, new PNIString(allocator, "power"));

            L.pushString(new PNIString(allocator, "square"));
            L.pushFunction(Tutorial06::square);
            L.setTable(-3);

            L.pushString(new PNIString(allocator, "cube"));
            L.pushFunction(Tutorial06::cube);
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

    private static int square(LuaState L) {
        double n = L.toNumber(-1);
        double result = n * n;
        L.pushNumber(result);
        return 1;
    }

    private static int cube(LuaState L) {
        double n = L.toNumber(-1);
        double result = n * n * n;
        L.pushNumber(result);
        return 1;
    }
}
