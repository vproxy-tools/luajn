#include <lua.h>
#include <lauxlib.h>
#include <stdlib.h>

static int luajn_PNIFuncRelease(lua_State *);
static int luajn_PNIFuncInvoke(lua_State *);
static int luajn_PNIRefRelease(lua_State *);

static int luajn_upcall_closure(lua_State *);
static int luajn_create_empty_module(lua_State *L) {
    lua_newtable(L);
    return 1;
}

// this is 5.2 compatible, the registered module will not appear in global variables
static void luajn_register_module(lua_State *L, char* libname) {
#if LUA_VERSION_NUM >= 502
    luaL_requiref(L, libname, luajn_create_empty_module, 0);
#else
    lua_getfield(L, LUA_REGISTRYINDEX, "_LOADED");
    if (!lua_istable(L, -1)) {
        lua_pop(L, 1);
        lua_newtable(L);
        lua_pushvalue(L, -1);
        lua_setfield(L, LUA_REGISTRYINDEX, "_LOADED");
    }
    lua_getfield(L, -1, libname);
    if (!lua_istable(L, -1)) {
        lua_pop(L, 1);
        lua_newtable(L);
        lua_pushvalue(L, -1);
        lua_setfield(L, -3, libname);
    }
    lua_insert(L, -2);
    lua_pop(L, 1);
#endif
}

#include "io_vproxy_luajn_n_LuaState.impl.h"
#include "io_vproxy_luajn_n_Lua.impl.h"
#include "io_vproxy_luajn_n_LuaLib.impl.h"
#include "io_vproxy_luajn_n_Helper.impl.h"
#include "io_vproxy_luajn_n_LuaDebug.h"
#include "io_vproxy_luajn_n_LuaDebugFuncs.impl.h"

#ifdef __GNUC__
    #define likely(x)       __builtin_expect(!!(x), 1)
    #define unlikely(x)     __builtin_expect(!!(x), 0)
#else
    #define likely(x)       (x)
    #define unlikely(x)     (x)
#endif

static int luajn_upcall_closure(lua_State *L) {
    if (unlikely(!lua_isuserdata(L, lua_upvalueindex(1)))) {
        lua_pushliteral(L, "luajn_upcall failed: The upValueIndex(1) element on stack is not a user data");
        lua_error(L);
        return 0;
    }
    PNIFunc* func = lua_touserdata(L, lua_upvalueindex(1));
    if (unlikely(func == NULL)) {
        lua_pushliteral(L, "luajn_upcall failed: The upValueIndex(1) element on stack is NULL");
        lua_error(L);
        return 0;
    }

    LuaState _LL;
    _LL.L = L;

    int ret = PNIFuncInvoke(func, &_LL);
    if (unlikely(ret == PNIFuncInvokeExceptionCaught)) {
        lua_pushliteral(L, "luajn_upcall failed: The call raised an exception on Java side");
        lua_error(L);
        return 0;
    }
    return ret;
}

static int luajn_PNIFuncInvoke(lua_State *L) {
    int top = lua_gettop(L);
    if (unlikely(top < 2)) {
        lua_pushliteral(L, "luajn_PNIFuncInvoke failed: Too few arguments provided");
        lua_error(L);
        return 0;
    }
    if (unlikely(top > 2)) {
        lua_pushliteral(L, "luajn_PNIFuncInvoke failed: Too many arguments");
        lua_error(L);
        return 0;
    }
    if (unlikely(!lua_isuserdata(L, -2) && !lua_islightuserdata(L, -2))) {
        lua_pushliteral(L, "luajn_PNIFuncInvoke failed: The first argument is not a user data");
        lua_error(L);
        return 0;
    }
    if (unlikely(!lua_isuserdata(L, -1) && !lua_islightuserdata(L, -1) && !lua_isnil(L, -1))) {
        lua_pushliteral(L, "luajn_PNIFuncInvoke failed: The second argument is not nil nor a user data");
        lua_error(L);
        return 0;
    }
    PNIFunc* func = lua_touserdata(L, -2);
    if (unlikely(func == NULL)) {
        lua_pushliteral(L, "luajn_PNIFuncInvoke failed: The first argument is NULL");
        lua_error(L);
        return 0;
    }
    void* obj;
    if (lua_isnil(L, -1)) {
        obj = NULL;
    } else {
        obj = lua_touserdata(L, -1);
    }
    int res = PNIFuncInvoke(func, obj);
    lua_pushinteger(L, res);
    return 1;
}

static int luajn_PNIFuncRelease(lua_State *L) {
    int top = lua_gettop(L);
    if (unlikely(top < 1)) {
        lua_pushliteral(L, "luajn_PNIFuncRelease failed: No arguments provided");
        lua_error(L);
        return 0;
    }
    if (unlikely(top > 1)) {
        lua_pushliteral(L, "luajn_PNIFuncRelease failed: Too many arguments");
        lua_error(L);
        return 0;
    }
    if (unlikely(!lua_isuserdata(L, 1) && !lua_islightuserdata(L, 1))) {
        lua_pushliteral(L, "luajn_PNIFuncRelease failed: the first argument is not a PNIFunc");
        lua_error(L);
        return 0;
    }
    PNIFunc* func = lua_touserdata(L, -1);
    if (unlikely(func == NULL)) {
        lua_pushliteral(L, "luajn_PNIFuncRelease failed: The first argument is NULL");
        lua_error(L);
        return 0;
    }
    PNIFuncRelease(func);
    return 0;
}

static int luajn_PNIRefRelease(lua_State *L) {
    int top = lua_gettop(L);
    if (unlikely(top < 1)) {
        lua_pushliteral(L, "luajn_PNIRefRelease failed: No arguments provided");
        lua_error(L);
        return 0;
    }
    if (unlikely(top > 1)) {
        lua_pushliteral(L, "luajn_PNIRefRelease failed: Too many arguments");
        lua_error(L);
        return 0;
    }
    if (unlikely(!lua_isuserdata(L, 1) && !lua_islightuserdata(L, 1))) {
        lua_pushliteral(L, "luajn_PNIRefRelease failed: the first argument is not a PNIRef");
        lua_error(L);
        return 0;
    }
    PNIRef* ref = lua_touserdata(L, -1);
    if (unlikely(ref == NULL)) {
        lua_pushliteral(L, "luajn_PNIRefRelease failed: The first argument is NULL");
        lua_error(L);
        return 0;
    }
    PNIRefRelease(ref);
    return 0;
}

JNIEXPORT int JNICALL luajn_ffi_upcall(PNIFunc* func, void* data) {
    return PNIFuncInvoke(func, data);
}

JNIEXPORT void JNICALL luajn_ffi_release_func(PNIFunc* func) {
    PNIFuncRelease(func);
}

JNIEXPORT void JNICALL luajn_ffi_release_ref(PNIRef* ref) {
    PNIRefRelease(ref);
}
