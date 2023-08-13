package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;

public class Tutorial15 {
    public static void main(String[] args) {
        LuaJNative.fullInit();
        System.out.println("Tutorial.12 Map/List");

        try (var allocator = Allocator.ofPooled(); var L = new LuaJNState(allocator)) {
            LuaLib.get().openLibs(L.getLuaState());

            try (var map = L.newTable()) {
                map.put("a", 1);
                map.put("b", "hello");
                map.forEach((k, v) -> System.out.println("map: key = " + k + ", v = " + v));
            }

            try (var ls = L.newTable()) {
                ls.set(1, 1);
                ls.set(2, "hello");
                System.out.println("array length = " + ls.arrayLength());
                for (int i = 1, len = ls.arrayLength(); i <= len; ++i) {
                    System.out.println("list [" + i + "] = " + ls.get(i));
                }
            }
        }
    }
}
