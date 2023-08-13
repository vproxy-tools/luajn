package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;

public class Tutorial13 {
    public static void main(String[] args) {
        LuaJNative.fullInit();
        System.out.println("Tutorial.12 Call Lua Function");

        try (var allocator = Allocator.ofPooled(); var L = new LuaJNState(allocator)) {
            LuaLib.get().openLibs(L.getLuaState());

            // language="lua"
            L.load("""
                function max(a, b)
                    if a > b then
                        return a
                    else
                        return b
                    end
                end
                """);

            var _G = L.getGlobal();

            var max = _G.getFunction("max");
            System.out.println("calling `max` from java ...");
            var n = (Number) max.invoke(38, 74);
            System.out.println("result = " + n.intValue());

            System.out.println("call again using a different way ...");
            var callRes = max.invoke(LL -> {
                LL.getLuaState().pushNumber(38);
                LL.getLuaState().pushNumber(74);
            });
            assert callRes.nresults == 1;
            n = (Number) callRes
                .convert(LL -> LL.getLuaState().toNumber(-1))
                // If there are multiple return values,
                // you should call `convert(...)` multiple times, with the same order as the results.
                // When `result()` is called, all `convert` lambda functions will be called from the last to the first,
                // to make it easy for you to pop values from the stack without worrying about index change.
                // You are allow to pop(1) or not pop the stack in the `convert` lambda functions,
                // the library will take care of the pop for you.
                // But you are not allowed to pop more than 1 element from the stack,
                // and you are not allowed to modify any other element on the stack except the top one.
                .result();
            System.out.println("result = " + n.intValue());
        }
    }
}
