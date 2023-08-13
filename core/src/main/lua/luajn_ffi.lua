-- luajn_ffi.lua is a luajn implementation which uses LuaJIT ffi features

local ffi = require("ffi")
ffi.cdef[[
    typedef struct { int64_t index; void* func; void* release; void* userdata; } PNIFunc;
    int luajn_ffi_upcall(void*,void*);
    void luajn_ffi_release_func(void*);
    void luajn_ffi_release_ref(void*);
]]

local module = {}

function module.upcall(func, obj)
    ffi.C.luajn_ffi_upcall(func, obj)
end

function module.release_func(func)
    ffi.C.luajn_ffi_release_func(func)
end

function module.release_ref(ref)
    ffi.C.luajn_ffi_release_ref(ref)
end

return module
