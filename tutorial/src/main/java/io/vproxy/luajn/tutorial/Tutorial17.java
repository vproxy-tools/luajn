package io.vproxy.luajn.tutorial;

import io.vproxy.luajn.LuaJNState;
import io.vproxy.luajn.LuaJNUserData;
import io.vproxy.luajn.LuaJNative;
import io.vproxy.luajn.n.Helper;
import io.vproxy.luajn.n.LuaLib;
import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.PNIString;

import java.lang.foreign.Arena;
import java.util.ArrayList;

public class Tutorial17 {
    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {
        System.loadLibrary("luajn-tutorial");
        TutorialUpcall.setImpl(TutorialUpcallImpl.get());
        LuaJNative.fullInit();
        System.out.println("Tutorial.17 Use Java Objects in Lua");

        System.out.println("ref storage size before operating: " + PNIRef.currentRefStorageSize());
        try (var arena = Arena.ofConfined(); var allocator = Allocator.ofPooled(); var L = new LuaJNState(allocator)) {
            LuaLib.get().openLibs(L.getLuaState());

            L.getGlobal().put("newJavaList", LL -> {
                PNIString.temporary("PNIRef", s -> {
                    LL.pushUserData(PNIRef.of(new ArrayList<String>()).MEMORY, PNIRef.LAYOUT.byteSize(), s);
                });
                return 1;
            });

            // language="lua"
            L.load("""
                local ffi = require("ffi")
                local luajn = require("luajn_ffi")
                                
                ffi.cdef[[
                    void luajn_tutorial_ls_add(void*,char*);
                    void luajn_tutorial_ls_remove(void*,int);
                    int luajn_tutorial_ls_size(void*);
                ]]
                                
                function main()
                    local ls = newList()
                    print("initial list size is", ls.size())
                    print("add string 'hello' to it")
                    ls.add('hello')
                    print("now the list size is", ls.size())
                    print("add multiple strings to it")
                    ls.add('a'); ls.add('b'); ls.add('world')
                    print("now the list size is", ls.size())
                    print("remove a few elements from it")
                    ls.remove(2); ls.remove(1)
                    print("now the list size is", ls.size())
                    return ls.self
                end
                                
                function newList()
                    local javaList = newJavaList();
                    tbl = {
                        self = javaList,
                        add = function(str)
                            local cStr = ffi.new("char[?]", #str + 1)
                            ffi.copy(cStr, str)
                            ffi.C.luajn_tutorial_ls_add(javaList, cStr)
                        end,
                        remove = function(index)
                            ffi.C.luajn_tutorial_ls_remove(javaList, index)
                        end,
                        size = function()
                            return ffi.C.luajn_tutorial_ls_size(javaList)
                        end,
                    }
                    return tbl
                end
                """);

            try (var main = L.getGlobal().getFunction("main")) {
                var ls = (LuaJNUserData) main.invoke();
                Helper.get().flushStdout();
                System.out.println("In Java, the list is: " + PNIRef.of(ls.MEMORY).getRef());
                System.out.println("ref storage size before closing: " + PNIRef.currentRefStorageSize());
            }
        }
        System.out.println("ref storage size after operating: " + PNIRef.currentRefStorageSize());
    }
}
