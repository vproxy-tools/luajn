# LuaJN

## What is LuaJN

`LuaJN = Lua Java Native`

This project is a Lua/C/Java binding, which enables the possibility to use any library made for standard Lua.

This project provides full binding for `openresty/luajit2`, while also provides supports for:

* 5.1
* 5.2
* 5.3
* latest (currently 5.4.6)
* luajit (latest commit)
* openresty/luajit2 (latest commit)
* ravi (5.3 compatible) (latest commit)

Theoretically any compatible implementation can work out of the box.

This project requires very little effort to maintain, since everything is simply a binding with some light-weight
high level api encapsulation with unittests.

## How to compile and test

This project requires `Java 21` to compile and run, and `Java 11 or 17` to build.  
Why need two JDKs? See [here](https://github.com/vproxy-tools/panama-native-interface#1-install-jdks).

If you are using Windows, you will need `MinGW UCRT`.

To make a full compile, you will need `openresty/luajit2`. Also, tutorials require `openresty/luajit2` as well.

Configure you environment variables:

* `JAVA_HOME`: The `JAVA_HOME` for `Java 11 or 17`.
* `JAVA_HOME_21`: The `JAVA_HOME` for `Java 21`.
* `LUA_INC`: The directory which contains Lua header files.
* `LUA_LD`: The directory which contains Lua dynamic library (the directory containing `.so|dylib|dll` file).
* `LUA_LIB`: The Lua library name, on Linux and macOS, you can extract the name from `lib${name}.so|dylib`; on Windows,
  you can extract the name from `${name}.dll`.

* Run `./gradlew clean pniCompile` to compile.
* Run `./gradlew clean runTutorial --console=plain -Did=XX` to run the sample code.
* Run `./gradlew clean runUnitTest` to run tests.
* Run `./gradlew clean jmh --console=plain` to run the benchmark.
* Run `git submodule update --init --recursive` to pull different versions of Lua source code, and
* Run `make test-all` to run tests on all these Lua versions.
* Run `make test-tutorial` to run all tutorials.

## How to use

You can use the low level native api which is almost the same as `Lua/C` api, they are located in
package `io.vproxy.luajn.n`.

You can also use the high level api which provides a Java style api, they are located in package `io.vproxy.luajn`.

`LuaJN` provides all data type bindings.

| Lua Type                  | To Java        | From Java                                |
|---------------------------|----------------|------------------------------------------|
| `LUA_TNONE` or `LUA_TNIL` | null           | null                                     |
| `LUA_TBOOLEAN`            | Boolean        | Boolean                                  |
| `LUA_TLIGHTUSERDATA`      | MemorySegment  | MemorySegment                            |
| `LUA_TNUMBER`             | Long or Double | Number                                   |
| `LUA_TSTRING`             | PNIString      | String or PNIString                      |
| `LUA_TTABLE`              | LuaJNTable     | LuaJNTable                               |
| `LUA_TFUNCTION`           | LuaJNFunction  | LuaJNFunction or `CallSite<LuaState>`    |
| `LUA_TUSERDATA`           | LuaJNUserData  | LuaJNUserData, `PNIFunc<T>`, `PNIRef<T>` |
| `LUA_TTHREAD`             | LuaJNState     | LuaJNState                               |

### Begin

Everything begins with `LuaJNState`, which corresponds to `lua_State *`.

```java
class Sample {
    public static void main(String[] args) {
        LuaJNative.defaultInit(); // load lua and luajn

        var allocator = Allocator.ofConfined();
        var L = new LuaJNState(allocator);
        L.load("""
            function hello(s) {
              print("hello", s)
            }
            """);
        try (var hello = L.getGlobal().getFunction("hello")) {
            hello.invoke("world");
        }

        // to release resources associated with LuaState, call:
        L.close();
    }
}
```

There are many detailed
tutorials [here](https://github.com/vproxy-tools/luajn/tree/master/tutorial/src/main/java/io/vproxy/luajn/tutorial).

> Note: `invoke(Object...)` might have bad performance because it runs multiple `if-instanceof` check on
> all input arguments, and uses reflection to check the parameter type for `CallSite<LuaState>` arguments.
> So only use it when performance is not an issue.  
> You can use `invoke(Consumer<LuaJNState>)` to get better performance,
> see
> [Tutorial13](https://github.com/vproxy-tools/luajn/blob/master/tutorial/src/main/java/io/vproxy/luajn/tutorial/Tutorial13.java)
> for more info.

### Load Lua and LuaJN

The defualt init function loads `lua` and `luajn`, and enables `Lua5.1` apis only.

```java
LuaJNative.defaultInit();
```

The full init function loads `lua` and all `luajn-*` libraries, which only works if you are using `openresty/luajit2`

```java
LuaJNative.fullInit();
```

You can choose to load any `lua` and `luajn-*` as you wish, simply do:

```java
LuaJNative.initOptions()
    // call setters here, they are method-chaining style api
    // you can set whether to load, the libname to load, or the filepath to load for every library
    .init();
```

### Call Java from Lua

LuaJN provides a very simple way for Lua scripts to call Java.

Use `CallSite<LuaState>`. This is a `functional interface`, which acts exactly the same as `CFunction`, only it can
be a lambda expression on the Java side which can capture variables.

You can pass `CallSite<LuaState>` as an argument to call a function, or store it as a global variable.  
On Lua side, it is a function, you can call it just like any other Lua function.

Also the `Callsite` can get gc-ed automatically, so you don't have to release the function manually.

---

You can also use the combination of `PNIFunc<T>` and `PNIRef<T>` to call Java from Lua.  
Like the `CallSite<LuaState>`, you can pass `PNIFunc<T>` and `PNIRef<T>` as arguments to call a function, or store
them as global variables.  
On Lua side, you can use `luajn.upcall(func, ref)` to invoke the function represented by `PNIFunc<T>`.

Also the `PNIFunc` and `PNIRef` can get gc-ed automatically, just like `CallSite`.

---

If you are using `luajit` or you have access to `ffi` libraries, you can generate function pointers using
`PanamaUtils.defineCFunction` or `PanamaUtils.defineCFunctionByName`, and then pass them to Lua as `lightuserdata`.

See
[Tutorial17](https://github.com/vproxy-tools/luajn/blob/master/tutorial/src/main/java/io/vproxy/luajn/tutorial/Tutorial17.java)
for more info.

### Low level API

The low level APIs are placed in package `io.vproxy.luajn.n`, you can choose to use them based on your native Lua
version.

* `Lua`, `LuaState`, `LuaLib`, `Helper` are very basic functions and consts, you can use them everywhere.
* The classes with a version suffix, e.g. `Lua5_2`, `Helper5_2`, does not work for a low version Lua.
* Constant values in `Helper` has a proper Java holder class: `io.vproxy.luajn.Consts`.
* Similar to the native classes, `Consts` classes also have version suffix.

### Panama Native Interface

This project heavily uses features provided
by [`Panama Native Interface`](https://github.com/vproxy-tools/panama-native-interface).

If you want to extend this project, for example, custom function interaction between Lua and Java, you will need to
check how to use that project.

## Coroutine Issues

Lua uses `longjmp` to implement its coroutines, which doesn't fit in JVM.  
It's safe to use coroutines only if `yield` doesn't cross JVM boundary.

The `LuaJN` doesn't provide `lua_yield` because if you call `yield` in Java, the `yield` always crosses JVM boundary,
and the JVM will either crash or leak resource. Also `LuaJN` doesn't provide any new coroutine-friendly functions
since `Lua5.2` simply because this project is mainly built for `openresty/luajit2`.

The Lua VM itself will also check whether `yield` crosses C-call boundary and raise an error. So it's likely to work
pretty well since you can only use `resume` on the Java side.

But `LuaJIT` allow you to yield across the C-call boundary. Please be really careful if you managed to `yield` across
the JVM boundary.

To be more specific, be careful if you managed to do this:

```
Java: Resume coroutine
            |
            |
            v
Lua: Call Java Function
            |
            |
            v
Java: Call Lua Function
            |
            |
            v
Lua:      yield
```

Just don't let `longjmp` mess up the `JVM` call stack.

## luajn module

You can use `luajn` module in Lua scripts.

```lua
local luajn = require("luajn")
-- or if you are using LuaJIT
local luajn = require("luajn_ffi")
-- they are the same, but the ffi version uses LuaJIT ffi
```

The module provides the following functions:

#### `luajn.upcall(func, obj)`

A wrapper for `PNIFuncInvoke(func, obj)`. The `func` and `obj` must be userdata or lightuserdata, and `func` should
be `PNIFunc` and `obj` will be `func`'s argument.

#### `luajn.release_func(func)`

A wrapper for `PNIFuncRelease(func)`. The `func` must be userdata or lightuserdata, and `func` should be `PNIFunc`.

#### `luajn.release_ref(ref)`

A wrapper for `PNIRefRelease(ref)`. The `ref` must be userdata or lightuserdata, and `ref` should be `PNIRef`.
