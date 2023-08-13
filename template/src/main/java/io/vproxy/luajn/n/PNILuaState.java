package io.vproxy.luajn.n;

import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@SuppressWarnings("unused")
@Struct
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
    @Critical
    abstract void close();

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_call(L, nargs, nresults);
            """
    )
    @Critical
    abstract void call(int nargs, int nresults);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_call(L, nargs, nresults);
            """
    )
    @Critical
    @Trivial
    abstract void callTrivial(int nargs, int nresults);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_checkstack(L, extra);
            """
    )
    @Trivial
    @Critical
    abstract boolean checkStack(int extra);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_concat(L, n);
            """
    )
    @Critical
    @Trivial
    abstract void concat(int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_createtable(L, narr, nrec);
            """
    )
    @Critical
    @Trivial
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
    @Trivial
    @Critical
    abstract boolean equal(int index1, int index2);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_error(L);
            """
    )
    @Trivial
    @Critical
    abstract int error();

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_getfield(L, index, k);
            """
    )
    @Trivial
    @Critical
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
    @Trivial
    @Critical
    abstract void getFEnv(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_getglobal(L, name);
            """
    )
    @Trivial
    @Critical
    abstract void getGlobal(String name);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_getmetatable(L, index);
            """
    )
    @Trivial
    @Critical
    abstract int getMetaTable(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_gettable(L, index);
            """
    )
    @Trivial
    @Critical
    abstract void getTable(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_gettop(L);
            """
    )
    @Trivial
    @Critical
    abstract int getTop();

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_insert(L, index);
            """
    )
    @Trivial
    @Critical
    abstract void insert(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isboolean(L, index);
            """
    )
    @Trivial
    @Critical
    abstract boolean isBoolean(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_iscfunction(L, index);
            """
    )
    @Trivial
    @Critical
    abstract boolean isCFunction(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isfunction(L, index);
            """
    )
    @Trivial
    @Critical
    abstract boolean isFunction(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_islightuserdata(L, index);
            """
    )
    @Trivial
    @Critical
    abstract boolean isLightUserData(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isnil(L, index);
            """
    )
    @Trivial
    @Critical
    abstract boolean isNil(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isnone(L, index);
            """
    )
    @Trivial
    @Critical
    abstract boolean isNone(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isnoneornil(L, index);
            """
    )
    @Trivial
    @Critical
    abstract boolean isNoneOrNil(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isnumber(L, index);
            """
    )
    @Trivial
    @Critical
    abstract boolean isNumber(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isstring(L, index);
            """
    )
    @Trivial
    @Critical
    abstract boolean isString(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_istable(L, index);
            """
    )
    @Trivial
    @Critical
    abstract boolean isTable(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isthread(L, index);
            """
    )
    @Trivial
    @Critical
    abstract boolean isThread(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_isuserdata(L, index);
            """
    )
    @Trivial
    @Critical
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
    @Trivial
    @Critical
    abstract boolean lessthan(int idx1, int idx2);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_newtable(L);
            """
    )
    @Trivial
    @Critical
    abstract void newTable();

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_newuserdata(L, size);
            """
    )
    @Trivial
    @Critical
    abstract MemorySegment newUserData(long size);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_next(L, index);
            """
    )
    @Trivial
    @Critical
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
    @Trivial
    @Critical
    abstract long objLen(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_pcall(L, nargs, nresults, errfunc);
            """
    )
    @Critical
    abstract int pcall(int nargs, int nresults, int errfunc);

    @Impl(
        // language="c"
        c = """
            return JavaCritical_io_vproxy_luajn_n_LuaState_pcall(self, nargs, nresults, errfunc);
            """
    )
    @Trivial
    @Critical
    abstract int pcallTrivial(int nargs, int nresults, int errfunc);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pop(L, n);
            """
    )
    @Trivial
    @Critical
    abstract void pop(int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushboolean(L, b);
            """
    )
    @Trivial
    @Critical
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
    @Trivial
    @Critical
    abstract void pushClosure(PNIFunc<PNILuaState> fn, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushcclosure(L, (lua_CFunction) fn, n);
            """
    )
    @Trivial
    @Critical
    abstract void pushCClosure(MemorySegment fn, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushcfunction(L, (lua_CFunction) fn);
            """
    )
    @Trivial
    @Critical
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
    @Trivial
    @Critical
    abstract void pushFunction(PNIFunc<PNILuaState> fn);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushinteger(L, n);
            """
    )
    @Trivial
    @Critical
    abstract void pushInteger(long n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushlightuserdata(L, p);
            """
    )
    @Trivial
    @Critical
    abstract void pushLightUserData(MemorySegment p);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushlstring(L, s, len);
            """
    )
    @Trivial
    @Critical
    abstract void pushLString(String s, long len);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushnil(L);
            """
    )
    @Trivial
    @Critical
    abstract void pushNil();

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushnumber(L, n);
            """
    )
    @Trivial
    @Critical
    abstract void pushNumber(double n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushstring(L, s);
            """
    )
    @Trivial
    @Critical
    abstract void pushString(String s);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_pushthread(L);
            """
    )
    @Trivial
    @Critical
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
    @Trivial
    @Critical
    abstract void pushUserData(MemorySegment data, long size, String metatableName);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_pushvalue(L, index);
            """
    )
    @Trivial
    @Critical
    abstract void pushValue(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_rawequal(L, idx1, idx2);
            """
    )
    @Trivial
    @Critical
    abstract boolean rawEqual(int idx1, int idx2);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_rawget(L, index);
            """
    )
    @Trivial
    @Critical
    abstract void rawGet(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_rawgeti(L, index, n);
            """
    )
    @Trivial
    @Critical
    abstract void rawGetI(int index, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_rawset(L, index);
            """
    )
    @Trivial
    @Critical
    abstract void rawSet(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_rawseti(L, index, n);
            """
    )
    @Trivial
    @Critical
    abstract void rawSetI(int index, int n);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_remove(L, index);
            """
    )
    @Trivial
    @Critical
    abstract void remove(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_replace(L, index);
            """
    )
    @Trivial
    @Critical
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
    @Trivial
    @Critical
    abstract int setFEnv(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_setfield(L, index, k);
            """
    )
    @Trivial
    @Critical
    abstract void setField(int index, String k);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_setglobal(L, name);
            """
    )
    @Trivial
    @Critical
    abstract void setGlobal(String name);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_setmetatable(L, index);
            """
    )
    @Trivial
    @Critical
    abstract int setMetaTable(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_settable(L, index);
            """
    )
    @Trivial
    @Critical
    abstract void setTable(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            lua_settop(L, index);
            """
    )
    @Trivial
    @Critical
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
    @Trivial
    @Critical
    abstract int strLen(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_toboolean(L, index);
            """
    )
    @Trivial
    @Critical
    abstract boolean toBoolean(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_tocfunction(L, index);
            """
    )
    @Trivial
    @Critical
    abstract MemorySegment toCFunction(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_tointeger(L, index);
            """
    )
    @Trivial
    @Critical
    abstract long toInteger(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return (char*) lua_tolstring(L, index, (size_t*) len);
            """
    )
    @Trivial
    @Critical
    abstract String toLString(int index, @Unsigned @Raw long[] len);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_tonumber(L, index);
            """
    )
    @Trivial
    @Critical
    abstract double toNumber(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return (void*) lua_topointer(L, index);
            """
    )
    @Trivial
    @Critical
    abstract MemorySegment toPointer(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return (char*) lua_tostring(L, index);
            """
    )
    @Trivial
    @Critical
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
    @Trivial
    @Critical
    abstract PNILuaState toThread(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_touserdata(L, index);
            """
    )
    @Trivial
    @Critical
    abstract MemorySegment toUserData(int index);

    @Impl(
        // language="c"
        c = """
            lua_State *L = self->L;
            return lua_type(L, index);
            """
    )
    @Trivial
    @Critical
    abstract int type(int index);
}
