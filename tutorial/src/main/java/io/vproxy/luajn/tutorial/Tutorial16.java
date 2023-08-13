package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.Consts;
import io.vproxy.luajn.n.Helper;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;

import java.util.Arrays;

public class Tutorial16 {
    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {
        LuaJNative.fullInit();
        System.out.println("Tutorial.16 Coroutine");

        try (var allocator = Allocator.ofPooled(); var L = new LuaJNState(allocator)) {
            LuaLib.get().openLibs(L.getLuaState());

            try (var co = L.loadCoroutine(allocator,
                // language="lua"
                """
                    return coroutine.create(function(a, b, c)
                        print("in Lua, resumes with", a, b, c)
                        local ab = a + b
                        local bc = b + c
                        print("in Lua, yields", ab, bc)
                        local x, y = coroutine.yield(ab, bc)
                        print("in Lua, resumes with", x, y)
                        local p = "prefix-" .. x
                        local s = y .. "-suffix"
                        print("in Lua, returns", p, s)
                        return p, s
                    end)
                    """)) {
                System.out.println("create coroutine in Lua");

                System.out.println("in Java, resumes with 1, 2, 3");
                var res = (Object[]) co.resume(1, 2, 3);
                Helper.get().flushStdout();

                System.out.println("in Java, receives yielded values " + Arrays.toString(res));
                System.out.println("current coroutine status = " + co.getStatus() + ", LUA_YIELD == " + Consts.LUA_YIELD);

                System.out.println("in Java, resumes with \"hello\", \"world\"");
                res = (Object[]) co.resume("hello", "world");
                Helper.get().flushStdout();

                System.out.println("in Java, receives returned values " + Arrays.toString(res));
                System.out.println("current coroutine status = " + co.getStatus());
            }

            System.out.println("-----------------------------------");

            try (var func = L.loadFunction(
                // language="lua"
                """
                    return function(a, b, c)
                        print("in Lua, resumes with", a, b, c)
                        local ab = a + b
                        local bc = b + c
                        print("in Lua, yields", ab, bc)
                        local x, y = coroutine.yield(ab, bc)
                        print("in Lua, resumes with", x, y)
                        local p = "prefix-" .. x
                        local s = y .. "-suffix"
                        print("in Lua, returns", p, s)
                        return p, s
                    end
                    """)) {
                System.out.println("create coroutine in Java");
                var co = L.newCoroutine(allocator);

                System.out.println("in Java, resumes with 1, 2, 3");
                var res = (Object[]) func.invoke(co, 1, 2, 3);
                Helper.get().flushStdout();

                System.out.println("in Java, receives yielded values " + Arrays.toString(res));
                System.out.println("current coroutine status = " + co.getStatus() + ", LUA_YIELD == " + Consts.LUA_YIELD);

                System.out.println("in Java, resumes with \"hello\", \"world\"");
                res = (Object[]) co.resume("hello", "world");
                Helper.get().flushStdout();

                System.out.println("in Java, receives returned values " + Arrays.toString(res));
                System.out.println("current coroutine status = " + co.getStatus());
            }
        }
    }
}
