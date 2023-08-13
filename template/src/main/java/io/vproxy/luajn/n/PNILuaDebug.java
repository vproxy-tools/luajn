package io.vproxy.luajn.n;

import io.vproxy.pni.annotation.Include;
import io.vproxy.pni.annotation.Len;
import io.vproxy.pni.annotation.Name;
import io.vproxy.pni.annotation.Struct;

@Include("<lua.h>")
@Struct(skip = true)
@Name("lua_Debug")
public class PNILuaDebug {
    int event;
    String name;
    @Name("namewhat") String nameWhat;
    String what;
    String source;
    @Name("currentline") int currentLine;
    @Name("nups") int nUps;
    @Name("linedefined") int lineDefined;
    @Name("lastlinedefined") int lastLineDefined;
    @Name("short_src") @Len(60) byte[] shortSrc;
    // private part
    // ...
}
