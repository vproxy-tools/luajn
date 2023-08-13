package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.Lua5_2;
import io.vproxy.luajn.n.LuaState;
import io.vproxy.luajn.n.Consts;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIString;

public class Tutorial02 {
    public static void main(String[] args) {
        LuaJNative.fullInit();
        System.out.println("Tutorial 02. Stack Operations");

        try (var allocator = Allocator.ofConfined()) {
            var L = Lua.get().newState(allocator);
            {
                System.out.println("push boolean true");
                L.pushBoolean(true);
            }
            {
                System.out.println("push integer 100");
                L.pushInteger(100);
            }
            {
                System.out.println("push number 10.011");
                L.pushNumber(10.011);
            }
            {
                System.out.println("push string \"hello\"");
                L.pushString(new PNIString(allocator, "hello"));
            }
            {
                System.out.println("push nil");
                L.pushNil();
            }

            stackDump(L);

            System.out.println("L.pushValue(1)");
            L.pushValue(1);
            stackDump(L);

            System.out.println("L.setTop(4)");
            L.setTop(4);
            stackDump(L);

            System.out.println("L.insert(3)");
            L.insert(3);
            stackDump(L);

            System.out.println("L.replace(3)");
            L.replace(3);
            stackDump(L);

            System.out.println("Lua5_2.get().copy(L, 2, 3)");
            Lua5_2.get().copy(L, 2, 3);
            stackDump(L);

            System.out.println("L.pop(3)");
            L.pop(3);
            stackDump(L);

            L.close();
        }
    }

    private static void stackDump(LuaState L) {
        System.out.print("[ ");
        int top = L.getTop();
        for (int i = 1; i <= top; ++i) {
            int t = L.type(i);
            if (t == Consts.LUA_TNIL) {
                System.out.print("nil");
            } else if (t == Consts.LUA_TBOOLEAN) {
                System.out.print(L.toBoolean(i));
            } else if (t == Consts.LUA_TNUMBER) {
                System.out.print(L.toNumber(i));
            } else if (t == Consts.LUA_TSTRING) {
                System.out.print(L.toString(i));
            } else {
                System.out.print(Lua.get().typename(L, t));
            }
            System.out.print(" ");
        }
        System.out.println("]");
    }
}
