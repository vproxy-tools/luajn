package io.vproxy.luajn.unittest;

import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.n.Helper;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIString;

import java.lang.foreign.Arena;

import static org.junit.Assert.assertEquals;

public class Utils {
    public static final Allocator GlobalAllocator = Allocator.of(Arena.ofShared());

    private Utils() {
    }

    public static LuaJNState create(String luaProgram) {
        var state = Lua.get().newState(GlobalAllocator);
        LuaLib.get().openLibs(state);
        var L = new LuaJNState(state);
        if (!luaProgram.isBlank()) {
            try (var allocator = Allocator.ofConfined()) {
                Lua.get().loadString(state, new PNIString(allocator, luaProgram));
            }
            int err = state.pcall(0, 0, 0);
            Helper.get().flushStdout();
            if (err != 0) {
                throw new RuntimeException(state.toString(-1).toString());
            }
        }
        postOpCheck(L);
        return L;
    }

    static void postOpCheck(LuaJNState L) {
        postOpCheck(L, 0);
    }

    static void postOpCheck(LuaJNState L, int stackSize) {
        assertEquals(stackSize, L.getLuaState().getTop());
    }
}
