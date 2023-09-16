if [[ -z "$suffix" ]]; then
  echo 'this script should be source-d by make*.sh'
  exit 1
fi

error=0

if [[ -z "$LUA_INC" ]]; then
  echo "You must set LUA_INC properly, where the lua includ files is located"
	error=1
fi

if [[ -z "$LUA_LD" ]]; then
  echo "You must set LUA_LD properly, where the lua shared library is located"
  error=1
fi

if [[ -z "$LUA_LIB" ]]; then
  echo "You must set LUA_LIB properly, which is the name of the lua library"
  error=1
fi

if [[ "$error" == "1" ]]; then
  exit 1
fi

os=`uname`

include_platform_dir=""

if [[ "Linux" == "$os" ]]; then
	include_platform_dir="linux"
elif [[ "Darwin" == "$os" ]]; then
	include_platform_dir="darwin"
else
	include_platform_dir="win32"
fi

set -e

for s in $suffix
do
  cname="core"
  target="luajn"
  if [[ "$s" != "-" ]]; then
    cname="$cname$s"
    target="$target$s"
  fi

  cname="$cname"".c"

  if [[ "Linux" == "$os" ]]; then
  	target="lib$target.so"
  elif [[ "Darwin" == "$os" ]]; then
  	target="lib$target.dylib"
  else
  	target="$target.dll"
  fi
  rm -f "$target"

  echo "compiling $cname to $target"
  gcc -std=gnu99 -O2 \
    $GCC_OPTS \
    -I "$LUA_INC" \
    -I "../../../../misc" \
    -I "../c-generated" \
    -L "$LUA_LD/" \
    -shared -Werror -lc -l$LUA_LIB -fPIC \
    "$cname" \
    -o "$target"
done
