package io.vproxy.luajn.n;

import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@SuppressWarnings("unused")
@Struct
@AlwaysAligned
@Include({
    "<lua.h>",
    "<lauxlib.h>",
    "<lualib.h>",
})
public abstract class PNILuaState {
    MemorySegment L;

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_close(L);
            """
    )
    @Style(Styles.critical)
    abstract void close();

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_call(L, nargs, nresults);
            """
    )
    @Style(Styles.critical)
    abstract void call(int nargs, int nresults);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_call(L, nargs, nresults);
            """
    )
    @Style(Styles.critical)
    @LinkerOption.Critical
    abstract void callTrivial(int nargs, int nresults);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_checkstack(L, extra);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean checkStack(int extra);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_concat(L, n);
            """
    )
    @Style(Styles.critical)
    @LinkerOption.Critical
    abstract void concat(int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_createtable(L, narr, nrec);
            """
    )
    @Style(Styles.critical)
    @LinkerOption.Critical
    abstract void createTable(int narr, int nrec);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            #if LUA_VERSION_NUM >= 502
            return lua_compare(L, index1, index2, LUA_OPEQ);
            #else
            return lua_equal(L, index1, index2);
            #endif
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean equal(int index1, int index2);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_error(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract int error();

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_getfield(L, index, k);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void getField(int index, String k);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            #if LUA_VERSION_NUM >= 502
            lua_pushnil(L);
            #else
            lua_getfenv(L, index);
            #endif
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void getFEnv(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_getglobal(L, name);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void getGlobal(String name);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_getmetatable(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract int getMetaTable(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_gettable(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void getTable(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_gettop(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract int getTop();

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_insert(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void insert(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isboolean(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean isBoolean(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_iscfunction(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean isCFunction(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isfunction(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean isFunction(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_islightuserdata(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean isLightUserData(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isnil(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean isNil(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isnone(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean isNone(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isnoneornil(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean isNoneOrNil(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isnumber(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean isNumber(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isstring(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean isString(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_istable(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean isTable(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isthread(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean isThread(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isuserdata(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean isUserdata(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            #if LUA_VERSION_NUM >= 502
            return lua_compare(L, idx1, idx2, LUA_OPLT);
            #else
            return lua_lessthan(L, idx1, idx2);
            #endif
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean lessthan(int idx1, int idx2);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_newtable(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void newTable();

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_newuserdata(L, size);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract MemorySegment newUserData(long size);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_next(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean next(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            #if LUA_VERSION_NUM >= 502
            return lua_rawlen(L, index);
            #else
            return lua_objlen(L, index);
            #endif
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract long objLen(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_pcall(L, nargs, nresults, errfunc);
            """
    )
    @Style(Styles.critical)
    abstract int pcall(int nargs, int nresults, int errfunc);

    @Impl(
        // language="c"
        c = """
            return JavaCritical_io_vproxy_luajn_n_LuaState_pcall(self, nargs, nresults, errfunc);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract int pcallTrivial(int nargs, int nresults, int errfunc);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pop(L, n);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pop(int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushboolean(L, b);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pushBoolean(boolean b);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            PNIFunc* copied = lua_newuserdata(L, sizeof(PNIFunc));
            *copied = *fn;
            luaL_getmetatable(L, "PNIFunc");
            lua_setmetatable(L, -2);

            lua_insert(L, -(n + 1));
            lua_pushcclosure(L, luajn_upcall_closure, n + 1);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pushClosure(PNIFunc<PNILuaState> fn, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushcclosure(L, (lua_CFunction) fn, n);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pushCClosure(MemorySegment fn, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushcfunction(L, (lua_CFunction) fn);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pushCFunction(MemorySegment fn);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            PNIFunc* copied = lua_newuserdata(L, sizeof(PNIFunc));
            *copied = *fn;
            luaL_getmetatable(L, "PNIFunc");
            lua_setmetatable(L, -2);

            lua_pushcclosure(L, luajn_upcall_closure, 1);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pushFunction(PNIFunc<PNILuaState> fn);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushinteger(L, n);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pushInteger(long n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushlightuserdata(L, p);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pushLightUserData(MemorySegment p);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushlstring(L, s, len);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pushLString(String s, long len);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushnil(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pushNil();

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushnumber(L, n);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pushNumber(double n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushstring(L, s);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pushString(String s);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_pushthread(L);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract int pushThread();

    @Impl(
        include = "<string.h>",
        // language="c"
        c = """
            lua_State *L = self->L;
            void* ud = lua_newuserdata(L, size);
            memcpy(ud, data, size);
                        
            if (metatableName != NULL) {
                luaL_getmetatable(L, metatableName);
                if (lua_istable(L, -1)) {
                    lua_setmetatable(L, -2);
                } else {
                    lua_pop(L, 1);
                }
            }
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pushUserData(MemorySegment data, long size, String metatableName);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushvalue(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void pushValue(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_rawequal(L, idx1, idx2);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean rawEqual(int idx1, int idx2);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_rawget(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void rawGet(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_rawgeti(L, index, n);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void rawGetI(int index, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_rawset(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void rawSet(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_rawseti(L, index, n);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void rawSetI(int index, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_remove(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void remove(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_replace(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void replace(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            #if LUA_VERSION_NUM >= 502
            lua_pop(L, 1);
            return 0;
            #else
            return lua_setfenv(L, index);
            #endif
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract int setFEnv(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_setfield(L, index, k);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void setField(int index, String k);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_setglobal(L, name);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void setGlobal(String name);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_setmetatable(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract int setMetaTable(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_settable(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void setTable(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_settop(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract void setTop(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            #if LUA_VERSION_NUM >= 502
            return lua_rawlen(L, index);
            #else
            return lua_strlen(L, index);
            #endif
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract int strLen(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_toboolean(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract boolean toBoolean(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_tocfunction(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract MemorySegment toCFunction(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_tointeger(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract long toInteger(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return (char*) lua_tolstring(L, index, (size_t*) len);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract String toLString(int index, @Unsigned @Raw long[] len);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_tonumber(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract double toNumber(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return (void*) lua_topointer(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract MemorySegment toPointer(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return (char*) lua_tostring(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract String toString(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_State *T = lua_tothread(L, index);
            if (T == NULL) return NULL;
            return_->L = T;
            return return_;
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract PNILuaState toThread(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_touserdata(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract MemorySegment toUserData(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_type(L, index);
            """
    )
    @LinkerOption.Critical
    @Style(Styles.critical)
    abstract int type(int index);
}
