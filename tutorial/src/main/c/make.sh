#!/bin/bash

error=0

if [[ -z "$JAVA_HOME" ]]
then
  echo "You must set JAVA_HOME properly"
	error=1
fi

if [[ -z "$LUA_INC" ]]
then
  echo "You must set LUA_INC properly"
	error=1
fi

if [[ -z "$LUA_LD" ]]
then
  echo "You must set LUA_LD properly"
  error=1
fi

if [[ "$error" == "1" ]]
then
  exit 1
fi

os=`uname`

target="luajn-tutorial"
include_platform_dir=""

if [[ "Linux" == "$os" ]]
then
	target="lib$target.so"
	include_platform_dir="linux"
elif [[ "Darwin" == "$os" ]]
then
	target="lib$target.dylib"
	include_platform_dir="darwin"
else
	target="$target.dll"
	include_platform_dir="win32"
fi

rm -f "$target"

gcc -std=gnu99 -O2 \
    $GCC_OPTS \
    -I "$JAVA_HOME/include" \
    -I "$JAVA_HOME/include/$include_platform_dir" \
    -I "$LUA_INC" \
    -I "../../../../misc" \
    -I "../c-generated" \
    -I "../../../../core/src/main/c-generated" \
    -L "$LUA_LD/" \
    -shared -Werror -lc -lluajit-5.1 -fPIC \
    tutorial.c \
    ../c-generated/io_vproxy_luajn_tutorial_TutorialUpcall.c \
    -o "$target"
