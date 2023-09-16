SHELL := /bin/bash
.DEFAULT_GOAL := test-all

.PHONY: test-all
test-all: test-lua-5.1 test-lua-5.2 test-lua-5.3 test-lua-latest test-lua-luajit test-lua-openresty test-lua-ravi

.PHONY: generate
generate:
	./gradlew pniGenerate

.PHONY: clean-luajn
clean-luajn:
	rm -f core/src/main/c/*.dylib
	rm -f core/src/main/c/*.so
	rm -f core/src/main/c/*.dll

.PHONY: test-pre
test-pre: clean-luajn generate

.PHONY: test-lua-5.1
test-lua-5.1: test-pre
	cd lua-source/lua-5.1/ && make liblua.so liblua.dylib lua.dll
	cd core/src/main/c/ && LUA_INC="../../../../lua-source/lua-5.1" LUA_LD="../../../../lua-source/lua-5.1" LUA_LIB="lua" ./make-5.1.sh
	LUA_LD="$(shell pwd)/lua-source/lua-5.1" ./gradlew clean runUnitTestNoCompile

.PHONY: test-lua-5.2
test-lua-5.2: test-pre
	cd lua-source/lua-5.2/ && make liblua.so liblua.dylib lua.dll
	cd core/src/main/c/ && LUA_INC="../../../../lua-source/lua-5.2" LUA_LD="../../../../lua-source/lua-5.2" LUA_LIB="lua" ./make-5.2.sh
	LOAD_LUAJN="502" LUA_LD="$(shell pwd)/lua-source/lua-5.2" ./gradlew clean runUnitTestNoCompile

.PHONY: test-lua-5.3
test-lua-5.3: test-pre
	cd lua-source/lua-5.3/ && make liblua.so liblua.dylib lua.dll
	cd core/src/main/c/ && LUA_INC="../../../../lua-source/lua-5.3" LUA_LD="../../../../lua-source/lua-5.3" LUA_LIB="lua" ./make-5.3.sh
	LOAD_LUAJN="503" LUA_LD="$(shell pwd)/lua-source/lua-5.3" ./gradlew clean runUnitTestNoCompile

.PHONY: test-lua-latest
test-lua-latest: test-pre
	cd lua-source/lua-latest/ && make liblua.so liblua.dylib lua.dll
	cd core/src/main/c/ && LUA_INC="../../../../lua-source/lua-latest" LUA_LD="../../../../lua-source/lua-latest" LUA_LIB="lua" ./make-5.3.sh
	LOAD_LUAJN="504" LUA_LD="$(shell pwd)/lua-source/lua-latest" ./gradlew clean runUnitTestNoCompile

.PHONY: test-lua-luajit
test-lua-luajit: test-pre
	cd lua-source/luajit && MACOSX_DEPLOYMENT_TARGET=10.8 make
	mkdir -p lua-source/luajit/install
	cd lua-source/luajit && make install PREFIX=`pwd`/install
	cd core/src/main/c/ && LUA_INC="../../../../lua-source/luajit/install/include/luajit-2.1" LUA_LD="../../../../lua-source/luajit/install/lib" LUA_LIB="luajit-5.1" ./make-luajit.sh
	LOAD_LUAJN="503" LOAD_LUAJIT="true" LUA_LD="$(shell pwd)/lua-source/luajit/install/lib" ./gradlew clean runUnitTestNoCompile

.PHONY: test-lua-openresty
test-lua-openresty: test-pre
	cd lua-source/openresty && MACOSX_DEPLOYMENT_TARGET=10.8 make
	mkdir -p lua-source/openresty/install
	cd lua-source/openresty && make install PREFIX=`pwd`/install
	cd core/src/main/c/ && LUA_INC="../../../../lua-source/openresty/install/include/luajit-2.1" LUA_LD="../../../../lua-source/openresty/install/lib" LUA_LIB="luajit-5.1" ./make-openresty.sh
	LOAD_LUAJN="503" LOAD_OPENRESTY="true" LUA_LD="$(shell pwd)/lua-source/openresty/install/lib" ./gradlew clean runUnitTestNoCompile

.PHONY: test-lua-ravi
test-lua-ravi: test-pre
	mkdir -p lua-source/ravi/build
	cd lua-source/ravi/build && cmake -DCMAKE_BUILD_TYPE=Release ..
	cd lua-source/ravi/build && make
	cd core/src/main/c/ && LUA_INC="../../../../lua-source/ravi/include" LUA_LD="../../../../lua-source/ravi/build" LUA_LIB="ravi" ./make-5.3.sh
	LOAD_LUAJN="503" LUA_LD="$(shell pwd)/lua-source/ravi/build" ./gradlew clean runUnitTestNoCompile

.PHONY: test-tutorials
test-tutorials:
	misc/test-tutorials.sh
