package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;

public class Tutorial14 {
    public static void main(String[] args) {
        LuaJNative.fullInit();
        System.out.println("Tutorial.12 Call Lua Function With Multiple Results");

        try (var allocator = Allocator.ofPooled(); var L = new LuaJNState(allocator)) {
            LuaLib.get().openLibs(L.getLuaState());

            // language="lua"
            var func = L.loadFunction("""
                return function()
                    return 1, "hello"
                end
                """);
            try (func) {
                System.out.println("calling from java ...");
                var result = (Object[]) func.invoke();
                System.out.println("result[0] = " + ((Number) result[0]).intValue());
                System.out.println("result[1] = " + result[1]);

                System.out.println("call again using a different way ...");
                result = (Object[]) func.invoke(LL -> {
                    })
                    .convert(LL -> LL.getLuaState().toNumber(-1))
                    .convert(LL -> LL.getLuaState().toString(-1))
                    .result();
                System.out.println("result[0] = " + ((Number) result[0]).intValue());
                System.out.println("result[1] = " + result[1]);
            }
        }
    }
}
