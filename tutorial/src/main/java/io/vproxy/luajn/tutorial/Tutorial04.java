package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.Helper;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIString;

public class Tutorial04 {
    public static void main(String[] args) throws Exception {
        LuaJNative.fullInit();
        System.out.println("Tutorial 04. Call Lua Function from Java");

        // language="lua"
        var lua = """
            function sayHello()
            	io.write("This is coming from lua.sayHello.\\n")
            end

            function add(a, b)
            	print("This is coming from lua.add. arg.a =", a, " arg.b =", b)
            	return a + b
            end
            """;

        try (var allocator = Allocator.ofConfined()) {
            var L = Lua.get().newState(allocator);
            LuaLib.get().openLibs(L);

            int err = Lua.get().loadString(L, new PNIString(allocator, lua));
            if (err != 0) {
                System.out.println("loadFile failed");
                return;
            }

            err = L.pcall(0, 0, 0);
            if (err != 0) {
                System.out.println("pcall() failed. " + L.toString(-1));
                return;
            }

            System.out.println("In Java, calling Lua->sayHello()");

            L.getGlobal(new PNIString(allocator, "sayHello"));
            err = L.pcall(0, 0, 0);
            if (err != 0) {
                System.out.println("pcall() failed");
                return;
            }
            Helper.get().flushStdout();

            System.out.println("Back in Java again");
            System.out.println();
            System.out.println("In Java, calling Lua->add()");

            L.getGlobal(new PNIString(allocator, "add"));
            L.pushNumber(1);
            L.pushNumber(5);
            err = L.pcall(2, 1, 0);
            if (err != 0) {
                System.out.println("pcall() failed");
                return;
            }
            Helper.get().flushStdout();

            System.out.println("Back in Java again");
            double returnNum = L.toNumber(-1);
            System.out.println("Returned number: " + returnNum);

            L.close();
        }
    }
}
