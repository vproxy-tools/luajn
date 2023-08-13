#include "io_vproxy_luajn_n_LuaState.h"
#include <string.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_close(LuaState * self) {
    lua_State *L = self->L;
    lua_close(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_call(LuaState * self, int32_t nargs, int32_t nresults) {
    lua_State *L = self->L;
    lua_call(L, nargs, nresults);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_callTrivial(LuaState * self, int32_t nargs, int32_t nresults) {
    lua_State *L = self->L;
    lua_call(L, nargs, nresults);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_checkStack(LuaState * self, int32_t extra) {
    lua_State *L = self->L;
    return lua_checkstack(L, extra);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_concat(LuaState * self, int32_t n) {
    lua_State *L = self->L;
    lua_concat(L, n);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_createTable(LuaState * self, int32_t narr, int32_t nrec) {
    lua_State *L = self->L;
    lua_createtable(L, narr, nrec);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_equal(LuaState * self, int32_t index1, int32_t index2) {
    lua_State *L = self->L;
    #if LUA_VERSION_NUM >= 502
    return lua_compare(L, index1, index2, LUA_OPEQ);
    #else
    return lua_equal(L, index1, index2);
    #endif
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_error(LuaState * self) {
    lua_State *L = self->L;
    return lua_error(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_getField(LuaState * self, int32_t index, char * k) {
    lua_State *L = self->L;
    lua_getfield(L, index, k);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_getFEnv(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    #if LUA_VERSION_NUM >= 502
    lua_pushnil(L);
    #else
    lua_getfenv(L, index);
    #endif
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_getGlobal(LuaState * self, char * name) {
    lua_State *L = self->L;
    lua_getglobal(L, name);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_getMetaTable(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_getmetatable(L, index);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_getTable(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    lua_gettable(L, index);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_getTop(LuaState * self) {
    lua_State *L = self->L;
    return lua_gettop(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_insert(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    lua_insert(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isBoolean(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_isboolean(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isCFunction(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_iscfunction(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isFunction(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_isfunction(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isLightUserData(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_islightuserdata(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isNil(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_isnil(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isNone(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_isnone(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isNoneOrNil(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_isnoneornil(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isNumber(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_isnumber(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isString(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_isstring(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isTable(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_istable(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isThread(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_isthread(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_isUserdata(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_isuserdata(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_lessthan(LuaState * self, int32_t idx1, int32_t idx2) {
    lua_State *L = self->L;
    #if LUA_VERSION_NUM >= 502
    return lua_compare(L, idx1, idx2, LUA_OPLT);
    #else
    return lua_lessthan(L, idx1, idx2);
    #endif
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_newTable(LuaState * self) {
    lua_State *L = self->L;
    lua_newtable(L);
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_newUserData(LuaState * self, int64_t size) {
    lua_State *L = self->L;
    return lua_newuserdata(L, size);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_next(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_next(L, index);
}

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_objLen(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    #if LUA_VERSION_NUM >= 502
    return lua_rawlen(L, index);
    #else
    return lua_objlen(L, index);
    #endif
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pcall(LuaState * self, int32_t nargs, int32_t nresults, int32_t errfunc) {
    lua_State *L = self->L;
    return lua_pcall(L, nargs, nresults, errfunc);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pcallTrivial(LuaState * self, int32_t nargs, int32_t nresults, int32_t errfunc) {
    return JavaCritical_io_vproxy_luajn_n_LuaState_pcall(self, nargs, nresults, errfunc);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pop(LuaState * self, int32_t n) {
    lua_State *L = self->L;
    lua_pop(L, n);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushBoolean(LuaState * self, uint8_t b) {
    lua_State *L = self->L;
    lua_pushboolean(L, b);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushClosure(LuaState * self, PNIFunc * fn, int32_t n) {
    lua_State *L = self->L;
    PNIFunc* copied = lua_newuserdata(L, sizeof(PNIFunc));
    *copied = *fn;
    luaL_getmetatable(L, "PNIFunc");
    lua_setmetatable(L, -2);

    lua_insert(L, -(n + 1));
    lua_pushcclosure(L, luajn_upcall_closure, n + 1);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushCClosure(LuaState * self, void * fn, int32_t n) {
    lua_State *L = self->L;
    lua_pushcclosure(L, (lua_CFunction) fn, n);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushCFunction(LuaState * self, void * fn) {
    lua_State *L = self->L;
    lua_pushcfunction(L, (lua_CFunction) fn);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushFunction(LuaState * self, PNIFunc * fn) {
    lua_State *L = self->L;
    PNIFunc* copied = lua_newuserdata(L, sizeof(PNIFunc));
    *copied = *fn;
    luaL_getmetatable(L, "PNIFunc");
    lua_setmetatable(L, -2);

    lua_pushcclosure(L, luajn_upcall_closure, 1);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushInteger(LuaState * self, int64_t n) {
    lua_State *L = self->L;
    lua_pushinteger(L, n);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushLightUserData(LuaState * self, void * p) {
    lua_State *L = self->L;
    lua_pushlightuserdata(L, p);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushLString(LuaState * self, char * s, int64_t len) {
    lua_State *L = self->L;
    lua_pushlstring(L, s, len);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushNil(LuaState * self) {
    lua_State *L = self->L;
    lua_pushnil(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushNumber(LuaState * self, double n) {
    lua_State *L = self->L;
    lua_pushnumber(L, n);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushString(LuaState * self, char * s) {
    lua_State *L = self->L;
    lua_pushstring(L, s);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushThread(LuaState * self) {
    lua_State *L = self->L;
    return lua_pushthread(L);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushUserData(LuaState * self, void * data, int64_t size, char * metatableName) {
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
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_pushValue(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    lua_pushvalue(L, index);
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_rawEqual(LuaState * self, int32_t idx1, int32_t idx2) {
    lua_State *L = self->L;
    return lua_rawequal(L, idx1, idx2);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_rawGet(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    lua_rawget(L, index);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_rawGetI(LuaState * self, int32_t index, int32_t n) {
    lua_State *L = self->L;
    lua_rawgeti(L, index, n);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_rawSet(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    lua_rawset(L, index);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_rawSetI(LuaState * self, int32_t index, int32_t n) {
    lua_State *L = self->L;
    lua_rawseti(L, index, n);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_remove(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    lua_remove(L, index);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_replace(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    lua_replace(L, index);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_setFEnv(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    #if LUA_VERSION_NUM >= 502
    lua_pop(L, 1);
    return 0;
    #else
    return lua_setfenv(L, index);
    #endif
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_setField(LuaState * self, int32_t index, char * k) {
    lua_State *L = self->L;
    lua_setfield(L, index, k);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_setGlobal(LuaState * self, char * name) {
    lua_State *L = self->L;
    lua_setglobal(L, name);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_setMetaTable(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_setmetatable(L, index);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_setTable(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    lua_settable(L, index);
}

JNIEXPORT void JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_setTop(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    lua_settop(L, index);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_strLen(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    #if LUA_VERSION_NUM >= 502
    return lua_rawlen(L, index);
    #else
    return lua_strlen(L, index);
    #endif
}

JNIEXPORT uint8_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toBoolean(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_toboolean(L, index);
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toCFunction(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_tocfunction(L, index);
}

JNIEXPORT int64_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toInteger(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_tointeger(L, index);
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toLString(LuaState * self, int32_t index, uint64_t * len) {
    lua_State *L = self->L;
    return (char*) lua_tolstring(L, index, (size_t*) len);
}

JNIEXPORT double JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toNumber(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_tonumber(L, index);
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toPointer(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return (void*) lua_topointer(L, index);
}

JNIEXPORT char * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toString(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return (char*) lua_tostring(L, index);
}

JNIEXPORT LuaState * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toThread(LuaState * self, int32_t index, LuaState * return_) {
    lua_State *L = self->L;
    lua_State *T = lua_tothread(L, index);
    if (T == NULL) return NULL;
    return_->L = T;
    return return_;
}

JNIEXPORT void * JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_toUserData(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_touserdata(L, index);
}

JNIEXPORT int32_t JNICALL JavaCritical_io_vproxy_luajn_n_LuaState_type(LuaState * self, int32_t index) {
    lua_State *L = self->L;
    return lua_type(L, index);
}

#ifdef __cplusplus
}
#endif
// metadata.generator-version: pni 21.0.0.8
// sha256:b60f709e68a2ae17299119ccc9ee122a3ce32d620a582dced3535929352a6867
