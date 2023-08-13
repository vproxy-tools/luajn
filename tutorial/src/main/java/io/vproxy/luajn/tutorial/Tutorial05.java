package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.Helper;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIString;

public class Tutorial05 {
    public static void main(String[] args) throws Exception {
        LuaJNative.fullInit();
        System.out.println("Tutorial 05. Visit Lua Table from Java");

        // language="lua"
        var lua = """
            function tablehandler(t)
              	local returnedt = {numfields = 1}
                                
               	for i, v in pairs(t) do
               		returnedt.numfields = returnedt.numfields + 1
               		returnedt[tostring(i)] = tostring(v)
               	end
                                
               	io.write("this is comming from table handler. table num fields : ", returnedt.numfields, "\\n")
               	return returnedt
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
                System.out.println("pcall() failed");
                return;
            }

            System.out.println("In Java, calling Lua->tablehandler()");

            L.getGlobal(new PNIString(allocator, "tablehandler"));
            L.newTable();
            L.pushString(new PNIString(allocator, "firstname"));
            L.pushString(new PNIString(allocator, "K.G."));
            L.setTable(-3);

            L.pushString(new PNIString(allocator, "lastname"));
            L.pushString(new PNIString(allocator, "Wang"));
            L.setTable(-3);

            err = L.pcall(1, 1, 0);
            if (err != 0) {
                System.out.println("pcall() failed");
                return;
            }
            Helper.get().flushStdout();

            System.out.println("============ Back in C again, Iterating thru returned table ============");

            L.pushNil(); // make sure lua_next starts at beginning
            while (L.next(-2)) {
                var v = L.toString(-1);
                var k = L.toString(-2);

                L.pop(1);

                System.out.println(k + " = " + v);
            }

            L.close();
        }
    }
}
