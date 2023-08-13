package io.vproxy.luajn.unittest;

import io.vproxy.luajn.LuaJNative;
import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings("NewClassNamingConvention")
public class BeforeAll {
    @BeforeClass
    public static void beforeClass() {
        var luajnVer = System.getenv("LOAD_LUAJN");
        if (luajnVer == null || luajnVer.isBlank()) {
            luajnVer = "501";
        }
        var luajit = "true".equals(System.getenv("LOAD_LUAJIT"));
        var openresty = "true".equals(System.getenv("LOAD_OPENRESTY"));
        try {
            var luajnVerN = Integer.parseInt(luajnVer);
            var opts = LuaJNative.initOptions();
            if (luajnVerN >= 502) {
                opts.setLoadLuaJN_5_2(true);
            }
            if (luajnVerN >= 503) {
                opts.setLoadLuaJN_5_3(true);
            }
            if (luajit || openresty) {
                opts.setLoadLuaJN_LuaJIT(true);
            }
            if (openresty) {
                opts.setLoadLuaJN_Openresty(true);
            }
            opts.init();
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    @Test
    public void dummy() {
    }
}
