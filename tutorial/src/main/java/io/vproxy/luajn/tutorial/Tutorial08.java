package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.Helper;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.luajn.n.LuaState;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIString;

import java.lang.foreign.ValueLayout;

public class Tutorial08 {
    public static void main(String[] args) throws Exception {
        LuaJNative.fullInit();
        System.out.println("Tutorial 08. Call Java Function from Lua: boolarray");

        // language="lua"
        var lua = """
            local array = require("boolarray")
                            
            a = array.new(100);
            print(array.size(a))
                            
            for i = 1, 100 do
                array.set(a, i, true)
            end
                            
            print(array.get(a, 10))
            array.set(a, 10, false)
            print(array.get(a, 10))
            """;

        try (var allocator = Allocator.ofConfined()) {
            var L = Lua.get().newState(allocator);
            LuaLib.get().openLibs(L);

            Lua.get().registerModule(L, new PNIString(allocator, "boolarray"));

            L.pushString(new PNIString(allocator, "new"));
            L.pushFunction(Tutorial08::newArray);
            L.setTable(-3);

            L.pushString(new PNIString(allocator, "set"));
            L.pushFunction(Tutorial08::setIntoArray);
            L.setTable(-3);

            L.pushString(new PNIString(allocator, "get"));
            L.pushFunction(Tutorial08::getFromArray);
            L.setTable(-3);

            L.pushString(new PNIString(allocator, "size"));
            L.pushFunction(Tutorial08::getSize);
            L.setTable(-3);

            L.pop(1);

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

    private static int newArray(LuaState L) {
        var n = L.toInteger(1);
        if (n < 1) {
            try (var allocator = Allocator.ofConfined()) {
                L.pushString(new PNIString(allocator, "invalid size"));
            }
            L.error();
            return 0;
        }

        long size = (n % 8 == 0) ? (n / 8) : (n / 8 + 1);
        size += 4; // int32
        var mem = L.newUserData(size).reinterpret(size);
        Helper.get().memset(mem, 0, size);
        mem.set(ValueLayout.JAVA_INT, 0, (int) n);

        return 1;
    }

    private static final int[] bits = new int[]{
        0b00000001, // 0
        0b00000010, // 1
        0b00000100, // 2
        0b00001000, // 3
        0b00010000, // 4
        0b00100000, // 5
        0b01000000, // 6
        0b10000000, // 7
    };

    @SuppressWarnings("SameReturnValue")
    private static int setIntoArray(LuaState L) {
        var mem = L.toUserData(1);
        int index = (int) (L.toInteger(2) - 1);

        if (mem == null) {
            try (var allocator = Allocator.ofConfined()) {
                L.pushString(new PNIString(allocator, "NullPointerException: array is null"));
            }
            L.error();
            return 0;
        }
        mem = mem.reinterpret(Integer.MAX_VALUE);
        int size = mem.get(ValueLayout.JAVA_INT, 0);
        mem = mem.asSlice(ValueLayout.JAVA_INT.byteSize());
        if (index < 0 || index >= size) {
            try (var allocator = Allocator.ofConfined()) {
                L.pushString(new PNIString(allocator, "ArrayIndexOutOfBoundsException: " + index + ", bounds " + size));
            }
            L.error();
            return 0;
        }

        if (L.toBoolean(3)) {
            byte b = mem.get(ValueLayout.JAVA_BYTE, index / 8);
            b |= bits[index % 8];
            mem.set(ValueLayout.JAVA_BYTE, index / 8, b);
        } else {
            byte b = mem.get(ValueLayout.JAVA_BYTE, index / 8);
            b &= ~(bits[index % 8]);
            mem.set(ValueLayout.JAVA_BYTE, index / 8, b);
        }

        return 0;
    }

    private static int getFromArray(LuaState L) {
        var mem = L.toUserData(1);
        int index = (int) (L.toInteger(2) - 1);

        if (mem == null) {
            try (var allocator = Allocator.ofConfined()) {
                L.pushString(new PNIString(allocator, "NullPointerException: array is null"));
            }
            L.error();
            return 0;
        }
        mem = mem.reinterpret(Integer.MAX_VALUE);
        int size = mem.get(ValueLayout.JAVA_INT, 0);
        mem = mem.asSlice(ValueLayout.JAVA_INT.byteSize());
        if (index < 0 || index >= size) {
            try (var allocator = Allocator.ofConfined()) {
                L.pushString(new PNIString(allocator, "ArrayIndexOutOfBoundsException: " + index + ", bounds " + size));
            }
            L.error();
            return 0;
        }

        byte b = mem.get(ValueLayout.JAVA_BYTE, index / 8);
        boolean res = (b & bits[index % 8]) != 0;
        L.pushBoolean(res);

        return 1;
    }

    private static int getSize(LuaState L) {
        var mem = L.toUserData(1);

        if (mem == null) {
            try (var allocator = Allocator.ofConfined()) {
                L.pushString(new PNIString(allocator, "NullPointerException: array is null"));
            }
            L.error();
            return 0;
        }
        mem = mem.reinterpret(Integer.MAX_VALUE);
        int size = mem.get(ValueLayout.JAVA_INT, 0);

        L.pushInteger(size);

        return 1;
    }
}
