package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.Helper;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.luajn.n.LuaState;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIString;

public class Tutorial09 {
    public static void main(String[] args) throws Exception {
        LuaJNative.fullInit();
        System.out.println("Tutorial 09. Call Java Function from Lua: sleeping: inject global variables to lua");

        // language="lua"
        var lua = """
            local start_time = os.time()
            print(string.format("started at %d", start_time))
                            
            sleep(1)
                            
            local mid_time = os.time()
            print(string.format("After sleep(1), time is %d", mid_time))
                            
            msleep(2000)
                          
            local end_time = os.time()
            print(string.format("After sleep(1), time is %d", end_time))
                            
            print(string.format("First  interval = %d seconds", mid_time - start_time))
            print(string.format("Second interval = %d seconds", end_time - mid_time))
            print(string.format("Whole  interval = %d seconds", end_time - start_time))
            """;

        try (var allocator = Allocator.ofConfined()) {
            var L = Lua.get().newState(allocator);
            LuaLib.get().openLibs(L);

            L.pushFunction(Tutorial09::sleep);
            L.setGlobal(new PNIString(allocator, "sleep"));

            L.pushFunction(Tutorial09::msleep);
            L.setGlobal(new PNIString(allocator, "msleep"));

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

    private static int sleep(LuaState L) {
        var seconds = L.toInteger(-1);

        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ignore) {
        }

        return 1;
    }

    private static int msleep(LuaState L) {
        var millis = L.toInteger(-1);

        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignore) {
        }

        return 1;
    }
}
