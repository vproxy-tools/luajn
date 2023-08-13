package io.vproxy.luajn;

import io.vproxy.luajn.n.Consts;
import io.vproxy.luajn.n.Lua;
import io.vproxy.luajn.n.LuaState;
import io.vproxy.pni.*;

import java.lang.foreign.MemorySegment;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

public class LuaJNative {
    private LuaJNative() {
    }

    public static void fullInit() {
        fullInitOptions().init();
    }

    public static Options fullInitOptions() {
        return initOptions()
            .setLoadLua(true)
            .setLoadLuaJN(true)
            .setLoadLuaJN_5_2(true)
            .setLoadLuaJN_5_3(true)
            .setLoadLuaJN_LuaJIT(true)
            .setLoadLuaJN_Openresty(true);
    }

    public static void defaultInit() {
        initOptions().init();
    }

    public static class Options {
        private boolean loadLua = true;
        private String luaLibName = null;
        private String luaLibAbsolutePath = null;

        private boolean loadLuaJN = true;
        private String luaJNLibName = null;
        private String luaJNLibAbsolutePath = null;

        private boolean loadLuaJN_5_2 = false;
        private String luaJN_5_2_LibName = null;
        private String luaJN_5_2_LibAbsolutePath = null;

        private boolean loadLuaJN_5_3 = false;
        private String luaJN_5_3_LibName = null;
        private String luaJN_5_3_LibAbsolutePath = null;

        private boolean loadLuaJN_LuaJIT = false;
        private String luaJN_LuaJIT_LibName = null;
        private String luaJN_LuaJIT_LibAbsolutePath = null;

        private boolean loadLuaJN_Openresty = false;
        private String luaJN_Openresty_LibName = null;
        private String luaJN_Openresty_LibAbsolutePath = null;

        private Options() {
        }

        public Options setLoadLua(boolean loadLua) {
            this.loadLua = loadLua;
            if (loadLua) {
                luaLibName = null;
                luaLibAbsolutePath = null;
            }
            return this;
        }

        public Options setLuaLibName(String luaLibName) {
            Objects.requireNonNull(luaLibName);
            loadLua = false;
            this.luaLibName = luaLibName;
            luaLibAbsolutePath = null;
            return this;
        }

        public Options setLuaLibAbsolutePath(String luaLibAbsolutePath) {
            Objects.requireNonNull(luaLibAbsolutePath);
            loadLua = false;
            luaLibName = null;
            this.luaLibAbsolutePath = luaLibAbsolutePath;
            return this;
        }

        public Options setLoadLuaJN(boolean loadLuaJN) {
            this.loadLuaJN = loadLuaJN;
            if (loadLuaJN) {
                luaJNLibName = null;
                luaJNLibAbsolutePath = null;
            }
            return this;
        }

        public Options setLuaJNLibName(String luaJNLibName) {
            Objects.requireNonNull(luaJNLibName);
            loadLuaJN = false;
            this.luaJNLibName = luaJNLibName;
            luaJNLibAbsolutePath = null;
            return this;
        }

        public Options setLuaJNLibAbsolutePath(String luaJNLibAbsolutePath) {
            Objects.requireNonNull(luaJNLibAbsolutePath);
            loadLuaJN = false;
            luaJNLibName = null;
            this.luaJNLibAbsolutePath = luaJNLibAbsolutePath;
            return this;
        }

        public Options setLoadLuaJN_5_2(boolean loadLuaJN_5_2) {
            this.loadLuaJN_5_2 = loadLuaJN_5_2;
            if (loadLuaJN_5_2) {
                luaJN_5_2_LibName = null;
                luaJN_5_2_LibAbsolutePath = null;
            }
            return this;
        }

        public Options setLuaJN_5_2_LibName(String luaJN_5_2_LibName) {
            Objects.requireNonNull(luaJN_5_2_LibName);
            loadLuaJN_5_2 = false;
            this.luaJN_5_2_LibName = luaJN_5_2_LibName;
            luaJN_5_2_LibAbsolutePath = null;
            return this;
        }

        public Options setLuaJN_5_2_LibAbsolutePath(String luaJN_5_2_LibAbsolutePath) {
            Objects.requireNonNull(luaJN_5_2_LibAbsolutePath);
            loadLuaJN_5_2 = false;
            luaJN_5_2_LibName = null;
            this.luaJN_5_2_LibAbsolutePath = luaJN_5_2_LibAbsolutePath;
            return this;
        }

        public Options setLoadLuaJN_5_3(boolean loadLuaJN_5_3) {
            this.loadLuaJN_5_3 = loadLuaJN_5_3;
            if (loadLuaJN_5_3) {
                luaJN_5_3_LibName = null;
                luaJN_5_3_LibAbsolutePath = null;
            }
            return this;
        }

        public Options setLuaJN_5_3_LibName(String luaJN_5_3_LibName) {
            Objects.requireNonNull(luaJN_5_3_LibName);
            loadLuaJN_5_3 = false;
            this.luaJN_5_3_LibName = luaJN_5_3_LibName;
            luaJN_5_3_LibAbsolutePath = null;
            return this;
        }

        public Options setLuaJN_5_3_LibAbsolutePath(String luaJN_5_3_LibAbsolutePath) {
            Objects.requireNonNull(luaJN_5_3_LibAbsolutePath);
            loadLuaJN_5_3 = false;
            luaJN_5_3_LibName = null;
            this.luaJN_5_3_LibAbsolutePath = luaJN_5_3_LibAbsolutePath;
            return this;
        }

        public Options setLoadLuaJN_LuaJIT(boolean loadLuaJN_LuaJIT) {
            this.loadLuaJN_LuaJIT = loadLuaJN_LuaJIT;
            if (loadLuaJN_LuaJIT) {
                luaJN_LuaJIT_LibName = null;
                luaJN_LuaJIT_LibAbsolutePath = null;
            }
            return this;
        }

        public Options setLuaJN_LuaJIT_LibName(String luaJN_LuaJIT_LibName) {
            Objects.requireNonNull(luaJN_LuaJIT_LibName);
            loadLuaJN_LuaJIT = false;
            this.luaJN_LuaJIT_LibName = luaJN_LuaJIT_LibName;
            luaJN_LuaJIT_LibAbsolutePath = null;
            return this;
        }

        public Options setLuaJN_LuaJIT_LibAbsolutePath(String luaJN_LuaJIT_LibAbsolutePath) {
            Objects.requireNonNull(luaJN_LuaJIT_LibAbsolutePath);
            loadLuaJN_LuaJIT = false;
            luaJN_LuaJIT_LibName = null;
            this.luaJN_LuaJIT_LibAbsolutePath = luaJN_LuaJIT_LibAbsolutePath;
            return this;
        }

        public Options setLoadLuaJN_Openresty(boolean loadLuaJN_Openresty) {
            this.loadLuaJN_Openresty = loadLuaJN_Openresty;
            if (loadLuaJN_Openresty) {
                luaJN_Openresty_LibName = null;
                luaJN_Openresty_LibAbsolutePath = null;
            }
            return this;
        }

        public Options setLuaJN_Openresty_LibName(String luaJN_Openresty_LibName) {
            Objects.requireNonNull(luaJN_Openresty_LibName);
            loadLuaJN_Openresty = false;
            this.luaJN_Openresty_LibName = luaJN_Openresty_LibName;
            luaJN_Openresty_LibAbsolutePath = null;
            return this;
        }

        public Options setLuaJN_Openresty_LibAbsolutePath(String luaJN_Openresty_LibAbsolutePath) {
            Objects.requireNonNull(luaJN_Openresty_LibAbsolutePath);
            loadLuaJN_Openresty = false;
            luaJN_Openresty_LibName = null;
            this.luaJN_Openresty_LibAbsolutePath = luaJN_Openresty_LibAbsolutePath;
            return this;
        }

        public void init() {
            if (loadLua) {
                defaultLoadLuaLibrary();
            } else if (luaLibName != null) {
                try {
                    System.loadLibrary(luaLibName);
                } catch (Throwable t) {
                    throw new UnsatisfiedLinkError(
                        "Unable to load " + luaLibName + ", " +
                        "current java.library.path is " + System.getProperty("java.library.path"));
                }
            } else if (luaLibAbsolutePath != null) {
                System.load(luaLibAbsolutePath);
            }
            if (loadLuaJN) {
                defaultLoadLuaJN();
            } else if (luaJNLibName != null) {
                try {
                    System.loadLibrary(luaJNLibName);
                } catch (Throwable t) {
                    throw new UnsatisfiedLinkError(
                        "Unable to load " + luaJNLibName + ", " +
                        "current java.library.path is " + System.getProperty("java.library.path"));
                }
            } else if (luaJNLibAbsolutePath != null) {
                System.load(luaJNLibAbsolutePath);
            }
            if (loadLuaJN_5_2) {
                defaultLoadLuaJN_5_2();
            } else if (luaJN_5_2_LibName != null) {
                try {
                    System.loadLibrary(luaJN_5_2_LibName);
                } catch (Throwable t) {
                    throw new UnsatisfiedLinkError(
                        "Unable to load " + luaJN_5_2_LibName + ", " +
                        "current java.library.path is " + System.getProperty("java.library.path"));
                }
            } else if (luaJN_5_2_LibAbsolutePath != null) {
                System.load(luaJN_5_2_LibAbsolutePath);
            }
            if (loadLuaJN_5_3) {
                defaultLoadLuaJN_5_3();
            } else if (luaJN_5_3_LibName != null) {
                try {
                    System.loadLibrary(luaJN_5_3_LibName);
                } catch (Throwable t) {
                    throw new UnsatisfiedLinkError(
                        "Unable to load " + luaJN_5_3_LibName + ", " +
                        "current java.library.path is " + System.getProperty("java.library.path"));
                }
            } else if (luaJN_5_3_LibAbsolutePath != null) {
                System.load(luaJN_5_3_LibAbsolutePath);
            }
            if (loadLuaJN_LuaJIT) {
                defaultLoadLuaJN_LuaJIT();
            } else if (luaJN_LuaJIT_LibName != null) {
                try {
                    System.loadLibrary(luaJN_LuaJIT_LibName);
                } catch (Throwable t) {
                    throw new UnsatisfiedLinkError(
                        "Unable to load " + luaJN_LuaJIT_LibName + ", " +
                        "current java.library.path is " + System.getProperty("java.library.path"));
                }
            } else if (luaJN_LuaJIT_LibAbsolutePath != null) {
                System.load(luaJN_LuaJIT_LibAbsolutePath);
            }
            if (loadLuaJN_Openresty) {
                defaultLoadLuaJN_Openresty();
            } else if (luaJN_Openresty_LibName != null) {
                try {
                    System.loadLibrary(luaJN_Openresty_LibName);
                } catch (Throwable t) {
                    throw new UnsatisfiedLinkError(
                        "Unable to load " + luaJN_Openresty_LibName + ", " +
                        "current java.library.path is " + System.getProperty("java.library.path"));
                }
            } else if (luaJN_Openresty_LibAbsolutePath != null) {
                System.load(luaJN_Openresty_LibAbsolutePath);
            }
            postLoaded();
        }
    }

    public static Options initOptions() {
        return new Options();
    }

    private static void defaultLoadLuaLibrary() {
        boolean ok;
        ok = tryLoad("luajit");
        if (ok) {
            return;
        }
        ok = tryLoad("luajit-5.1");
        if (ok) {
            return;
        }
        ok = tryLoad("ravi");
        if (ok) {
            return;
        }
        ok = tryLoad("lua");
        if (ok) {
            return;
        }
        throw new UnsatisfiedLinkError("Unable to load luajit,luajit-5.1,ravi,lua, " +
                                       "current java.library.path is " + System.getProperty("java.library.path"));
    }

    private static boolean tryLoad(String libname) {
        try {
            System.loadLibrary(libname);
            System.out.println("[luajn] " + libname + " loaded");
        } catch (Throwable ignore) {
            return false;
        }
        return true;
    }

    private static void defaultLoadLuaJN() {
        try {
            System.loadLibrary("luajn");
            System.out.println("[luajn] luajn loaded");
        } catch (Throwable ignore) {
            throw new UnsatisfiedLinkError(
                "Unable to load luajn, " +
                "current java.library.path is " + System.getProperty("java.library.path"));
        }
    }

    private static void defaultLoadLuaJN_5_2() {
        try {
            System.loadLibrary("luajn-5.2");
            System.out.println("[luajn] luajn-5.2 loaded");
        } catch (Throwable ignore) {
            throw new UnsatisfiedLinkError(
                "Unable to load luajn-5.2, " +
                "current java.library.path is " + System.getProperty("java.library.path"));
        }
    }

    private static void defaultLoadLuaJN_5_3() {
        try {
            System.loadLibrary("luajn-5.3");
            System.out.println("[luajn] luajn-5.3 loaded");
        } catch (Throwable ignore) {
            throw new UnsatisfiedLinkError(
                "Unable to load luajn-5.3, " +
                "current java.library.path is " + System.getProperty("java.library.path"));
        }
    }

    private static void defaultLoadLuaJN_LuaJIT() {
        try {
            System.loadLibrary("luajn-luajit");
            System.out.println("[luajn] luajn-luajit loaded");
        } catch (Throwable ignore) {
            throw new UnsatisfiedLinkError(
                "Unable to load luajn-luajit, " +
                "current java.library.path is " + System.getProperty("java.library.path"));
        }
    }

    private static void defaultLoadLuaJN_Openresty() {
        try {
            System.loadLibrary("luajn-openresty");
            System.out.println("[luajn] luajn-openresty loaded");
        } catch (Throwable ignore) {
            throw new UnsatisfiedLinkError(
                "Unable to load luajn-openresty, " +
                "current java.library.path is " + System.getProperty("java.library.path"));
        }
    }

    private static void postLoaded() {
        // do nothing for now
    }

    public static void pushStack(Object o, LuaJNState ctxL, Allocator allocator) {
        var L = ctxL.getLuaState();
        if (o == null) {
            L.pushNil();
            return;
        }
        if (o instanceof Integer i) {
            L.pushInteger(i);
        } else if (o instanceof Long l) {
            L.pushInteger(l);
        } else if (o instanceof Double d) {
            L.pushNumber(d);
        } else if (o instanceof String s) {
            L.pushString(new PNIString(allocator, s));
        } else if (o instanceof LuaJNTable table) {
            table.__push(ctxL);
        } else if (o instanceof LuaJNFunction func) {
            func.__push(ctxL);
        } else if (o instanceof CallSite<?>) {
            //noinspection rawtypes
            checkCallSiteType((CallSite) o);
            //noinspection unchecked
            L.pushFunction((CallSite<LuaState>) o);
        } else if (o instanceof PNIFunc<?> func) {
            PNIString.temporary("PNIFunc", s -> {
                L.pushUserData(func.MEMORY, PNIFunc.LAYOUT.byteSize(), s);
            });
        } else if (o instanceof PNIRef<?> ref) {
            PNIString.temporary("PNIRef", s -> {
                L.pushUserData(ref.MEMORY, PNIRef.LAYOUT.byteSize(), s);
            });
        } else if (o instanceof Boolean b) {
            L.pushBoolean(b);
        } else if (o instanceof MemorySegment p) {
            L.pushLightUserData(p);
        } else if (o instanceof LuaJNUserData u) {
            u.__push(ctxL);
        } else if (o instanceof LuaJNState t) {
            t.__push(ctxL);
        } else {
            throw new UnsupportedOperationException("unsupported java type " + o.getClass());
        }
    }

    private static void checkCallSiteType(@SuppressWarnings("rawtypes") CallSite o) {
        var interfaces = o.getClass().getGenericInterfaces();
        Type callsiteInterface = null;
        for (var i : interfaces) {
            if (i.getTypeName().startsWith(CallSite.class.getName())) {
                callsiteInterface = i;
                break;
            }
        }
        if (!(callsiteInterface instanceof ParameterizedType t)) {
            throw new IllegalArgumentException("Unable to check CallSite type");
        }
        var args = t.getActualTypeArguments();
        if (args.length != 1) {
            throw new IllegalArgumentException("Invalid parameterized type info");
        }

        if (!LuaState.class.getName().equals(args[0].getTypeName())) {
            throw new IllegalArgumentException("Only CallSite<LuaState> can be used. " +
                                               "If you want to pass a custom function to Lua, use the T.Func or PNIFunc.VoidFunc wrappers instead");
        }
    }

    public static Object formatResult(LuaJNState ctxL, Allocator allocator) {
        var L = ctxL.getLuaState();
        var type = L.type(-1);
        if (type == Consts.LUA_TNUMBER) {
            var res = L.toNumber(-1);
            L.pop(1);
            return res;
        } else if (type == Consts.LUA_TSTRING) {
            var res = L.toString(-1).toString();
            L.pop(1);
            return res;
        } else if (type == Consts.LUA_TNIL || type == Consts.LUA_TNONE) {
            L.pop(1);
            return null;
        } else if (type == Consts.LUA_TTABLE) {
            var tmpVar = ctxL.storeTemporaryVariable();
            return new LuaJNTable(ctxL, -1, tmpVar);
        } else if (type == Consts.LUA_TFUNCTION) {
            var tmpVar = ctxL.storeTemporaryVariable();
            return new LuaJNFunction(ctxL, tmpVar);
        } else if (type == Consts.LUA_TBOOLEAN) {
            var res = L.toBoolean(-1);
            L.pop(1);
            return res;
        } else if (type == Consts.LUA_TLIGHTUSERDATA) {
            var res = L.toUserData(-1);
            L.pop(1);
            return res;
        } else if (type == Consts.LUA_TUSERDATA) {
            var mem = L.toUserData(-1);
            var tmpVar = ctxL.storeTemporaryVariable();
            return new LuaJNUserData(ctxL, tmpVar, mem);
        } else if (type == Consts.LUA_TTHREAD) {
            var T = L.toThread(-1, allocator);
            var tmpVar = ctxL.storeTemporaryVariable();
            return new LuaJNState(T, ctxL, tmpVar);
        }
        throw new UnsupportedOperationException("unsupported lua type " + Lua.get().typename(L, type));
    }
}
