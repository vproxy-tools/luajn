package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.Helper;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIString;

import java.util.Scanner;

public class Tutorial01 {
    public static void main(String[] args) {
        LuaJNative.fullInit();
        System.out.println("Tutorial 01. Simple One-Line Lua REPL");
        System.out.println("It's recommended to add \"--console=plain\" when running from Gradle.");
        try (var allocator = Allocator.ofConfined(); var sc = new Scanner(System.in)) {
            var L = Lua.get().newState(allocator);
            LuaLib.get().openLibs(L);

            while (true) {
                System.out.print("> ");
                var line = sc.nextLine();
                if (line == null) {
                    L.close();
                    break;
                }
                if (line.isBlank()) {
                    continue;
                }
                int err = Lua.get().loadString(L, new PNIString(allocator, line));
                if (err == 0) {
                    err = L.pcall(0, 0, 0);
                }
                if (err != 0) {
                    System.out.println(L.toString(-1));
                    L.pop(1);
                }
                if (err == 0) {
                    Helper.get().flushStdout();
                }
            }
        }
    }
}
