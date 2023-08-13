package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.Helper;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIString;

public class Tutorial03 {
    public static void main(String[] args) throws Exception {
        LuaJNative.fullInit();
        System.out.println("Tutorial 03. Call Lua from Java");

        // language="lua"
        var lua = """
            io.write("This is coming from lua\\n")
            """;

        try (var allocator = Allocator.ofConfined()) {
            var L = Lua.get().newState(allocator);
            LuaLib.get().openLibs(L);

            int err = Lua.get().loadString(L, new PNIString(allocator, lua));
            if (err != 0) {
                System.out.println("loadFile failed");
                return;
            }

            System.out.println("In Java, calling Lua");

            err = L.pcall(0, 0, 0);
            if (err != 0) {
                System.out.println("pcall() failed. " + L.toString(-1));
                return;
            }
            Helper.get().flushStdout();

            System.out.println("Back in Java again");

            L.close();
        }
    }
}
