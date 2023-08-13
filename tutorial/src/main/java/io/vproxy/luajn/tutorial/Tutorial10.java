package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.Helper;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIString;

public class Tutorial10 {
    public static void main(String[] args) throws Exception {
        LuaJNative.fullInit();
        System.loadLibrary("luajn-tutorial");
        System.out.println("Tutorial 10. Require luajn");

        // language="lua"
        var lua = """
            local ffi = require "ffi"
            ffi.cdef[[
            typedef struct { int32_t intValue; int64_t longValue; } TutorialNative;
            ]]
            local luajn = require "luajn"
            print("This is a tutorial which requires \\"luajn\\"")
            print("You can enable it using Lua.get().openLibs(L) or Lua.get().openLuaJN(L)")
                            
            -- variable "func", "obj" are set from Java
                            
            cobj = ffi.cast("TutorialNative*", obj)
            print("before upcall:")
            print("intValue", cobj.intValue)
            print("longValue", cobj.longValue)
                            
            luajn.upcall(func, obj)
                            
            print("after upcall:")
            print("intValue", cobj.intValue)
            print("longValue", cobj.longValue)
            luajn.release_func(func)
            """;

        try (var allocator = Allocator.ofConfined()) {
            var L = Lua.get().newState(allocator);
            LuaLib.get().openLibs(L);

            var tutorial = new TutorialNative(allocator);
            var func = TutorialNative.Func.of(Tutorial10::func);
            L.pushLightUserData(func.MEMORY);
            L.setGlobal(new PNIString(allocator, "func"));
            L.pushLightUserData(tutorial.MEMORY);
            L.setGlobal(new PNIString(allocator, "obj"));

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

    private static int func(TutorialNative t) {
        t.setIntValue(12);
        t.setLongValue(93);
        return 0;
    }
}
