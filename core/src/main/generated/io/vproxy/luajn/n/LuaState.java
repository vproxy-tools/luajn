package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class LuaState extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.ADDRESS.withName("L")
    ).withByteAlignment(8);
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle LVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("L")
    );

    public MemorySegment getL() {
        var SEG = (MemorySegment) LVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return SEG;
    }

    public void setL(MemorySegment L) {
        if (L == null) {
            LVH.set(MEMORY, MemorySegment.NULL);
        } else {
            LVH.set(MEMORY, L);
        }
    }

    public LuaState(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
    }

    public LuaState(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle closeMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_close", MemorySegment.class /* self */);

    public void close() {
        try {
            closeMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle callMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_call", MemorySegment.class /* self */, int.class /* nargs */, int.class /* nresults */);

    public void call(int nargs, int nresults) {
        try {
            callMH.invokeExact(MEMORY, nargs, nresults);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle callTrivialMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_callTrivial", MemorySegment.class /* self */, int.class /* nargs */, int.class /* nresults */);

    public void callTrivial(int nargs, int nresults) {
        try {
            callTrivialMH.invokeExact(MEMORY, nargs, nresults);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle checkStackMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_checkStack", MemorySegment.class /* self */, int.class /* extra */);

    public boolean checkStack(int extra) {
        boolean RESULT;
        try {
            RESULT = (boolean) checkStackMH.invokeExact(MEMORY, extra);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle concatMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_concat", MemorySegment.class /* self */, int.class /* n */);

    public void concat(int n) {
        try {
            concatMH.invokeExact(MEMORY, n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle createTableMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_createTable", MemorySegment.class /* self */, int.class /* narr */, int.class /* nrec */);

    public void createTable(int narr, int nrec) {
        try {
            createTableMH.invokeExact(MEMORY, narr, nrec);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle equalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_equal", MemorySegment.class /* self */, int.class /* index1 */, int.class /* index2 */);

    public boolean equal(int index1, int index2) {
        boolean RESULT;
        try {
            RESULT = (boolean) equalMH.invokeExact(MEMORY, index1, index2);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle errorMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_LuaState_error", MemorySegment.class /* self */);

    public int error() {
        int RESULT;
        try {
            RESULT = (int) errorMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle getFieldMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_getField", MemorySegment.class /* self */, int.class /* index */, String.class /* k */);

    public void getField(int index, PNIString k) {
        try {
            getFieldMH.invokeExact(MEMORY, index, (MemorySegment) (k == null ? MemorySegment.NULL : k.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle getFEnvMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_getFEnv", MemorySegment.class /* self */, int.class /* index */);

    public void getFEnv(int index) {
        try {
            getFEnvMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle getGlobalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_getGlobal", MemorySegment.class /* self */, String.class /* name */);

    public void getGlobal(PNIString name) {
        try {
            getGlobalMH.invokeExact(MEMORY, (MemorySegment) (name == null ? MemorySegment.NULL : name.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle getMetaTableMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_LuaState_getMetaTable", MemorySegment.class /* self */, int.class /* index */);

    public int getMetaTable(int index) {
        int RESULT;
        try {
            RESULT = (int) getMetaTableMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle getTableMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_getTable", MemorySegment.class /* self */, int.class /* index */);

    public void getTable(int index) {
        try {
            getTableMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle getTopMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_LuaState_getTop", MemorySegment.class /* self */);

    public int getTop() {
        int RESULT;
        try {
            RESULT = (int) getTopMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle insertMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_insert", MemorySegment.class /* self */, int.class /* index */);

    public void insert(int index) {
        try {
            insertMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle isBooleanMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_isBoolean", MemorySegment.class /* self */, int.class /* index */);

    public boolean isBoolean(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) isBooleanMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle isCFunctionMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_isCFunction", MemorySegment.class /* self */, int.class /* index */);

    public boolean isCFunction(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) isCFunctionMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle isFunctionMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_isFunction", MemorySegment.class /* self */, int.class /* index */);

    public boolean isFunction(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) isFunctionMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle isLightUserDataMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_isLightUserData", MemorySegment.class /* self */, int.class /* index */);

    public boolean isLightUserData(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) isLightUserDataMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle isNilMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_isNil", MemorySegment.class /* self */, int.class /* index */);

    public boolean isNil(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) isNilMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle isNoneMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_isNone", MemorySegment.class /* self */, int.class /* index */);

    public boolean isNone(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) isNoneMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle isNoneOrNilMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_isNoneOrNil", MemorySegment.class /* self */, int.class /* index */);

    public boolean isNoneOrNil(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) isNoneOrNilMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle isNumberMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_isNumber", MemorySegment.class /* self */, int.class /* index */);

    public boolean isNumber(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) isNumberMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle isStringMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_isString", MemorySegment.class /* self */, int.class /* index */);

    public boolean isString(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) isStringMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle isTableMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_isTable", MemorySegment.class /* self */, int.class /* index */);

    public boolean isTable(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) isTableMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle isThreadMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_isThread", MemorySegment.class /* self */, int.class /* index */);

    public boolean isThread(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) isThreadMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle isUserdataMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_isUserdata", MemorySegment.class /* self */, int.class /* index */);

    public boolean isUserdata(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) isUserdataMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle lessthanMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_lessthan", MemorySegment.class /* self */, int.class /* idx1 */, int.class /* idx2 */);

    public boolean lessthan(int idx1, int idx2) {
        boolean RESULT;
        try {
            RESULT = (boolean) lessthanMH.invokeExact(MEMORY, idx1, idx2);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle newTableMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_newTable", MemorySegment.class /* self */);

    public void newTable() {
        try {
            newTableMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle newUserDataMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), MemorySegment.class, "JavaCritical_io_vproxy_luajn_n_LuaState_newUserData", MemorySegment.class /* self */, long.class /* size */);

    public MemorySegment newUserData(long size) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) newUserDataMH.invokeExact(MEMORY, size);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle nextMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_next", MemorySegment.class /* self */, int.class /* index */);

    public boolean next(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) nextMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle objLenMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), long.class, "JavaCritical_io_vproxy_luajn_n_LuaState_objLen", MemorySegment.class /* self */, int.class /* index */);

    public long objLen(int index) {
        long RESULT;
        try {
            RESULT = (long) objLenMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle pcallMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), int.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pcall", MemorySegment.class /* self */, int.class /* nargs */, int.class /* nresults */, int.class /* errfunc */);

    public int pcall(int nargs, int nresults, int errfunc) {
        int RESULT;
        try {
            RESULT = (int) pcallMH.invokeExact(MEMORY, nargs, nresults, errfunc);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle pcallTrivialMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pcallTrivial", MemorySegment.class /* self */, int.class /* nargs */, int.class /* nresults */, int.class /* errfunc */);

    public int pcallTrivial(int nargs, int nresults, int errfunc) {
        int RESULT;
        try {
            RESULT = (int) pcallTrivialMH.invokeExact(MEMORY, nargs, nresults, errfunc);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle popMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pop", MemorySegment.class /* self */, int.class /* n */);

    public void pop(int n) {
        try {
            popMH.invokeExact(MEMORY, n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle pushBooleanMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushBoolean", MemorySegment.class /* self */, boolean.class /* b */);

    public void pushBoolean(boolean b) {
        try {
            pushBooleanMH.invokeExact(MEMORY, b);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle pushClosureMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushClosure", MemorySegment.class /* self */, io.vproxy.pni.CallSite.class /* fn */, int.class /* n */);

    public void pushClosure(io.vproxy.pni.CallSite<io.vproxy.luajn.n.LuaState> fn, int n) {
        try {
            pushClosureMH.invokeExact(MEMORY, (MemorySegment) (fn == null ? MemorySegment.NULL : io.vproxy.luajn.n.LuaState.Func.of(fn).MEMORY), n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle pushCClosureMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushCClosure", MemorySegment.class /* self */, MemorySegment.class /* fn */, int.class /* n */);

    public void pushCClosure(MemorySegment fn, int n) {
        try {
            pushCClosureMH.invokeExact(MEMORY, (MemorySegment) (fn == null ? MemorySegment.NULL : fn), n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle pushCFunctionMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushCFunction", MemorySegment.class /* self */, MemorySegment.class /* fn */);

    public void pushCFunction(MemorySegment fn) {
        try {
            pushCFunctionMH.invokeExact(MEMORY, (MemorySegment) (fn == null ? MemorySegment.NULL : fn));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle pushFunctionMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushFunction", MemorySegment.class /* self */, io.vproxy.pni.CallSite.class /* fn */);

    public void pushFunction(io.vproxy.pni.CallSite<io.vproxy.luajn.n.LuaState> fn) {
        try {
            pushFunctionMH.invokeExact(MEMORY, (MemorySegment) (fn == null ? MemorySegment.NULL : io.vproxy.luajn.n.LuaState.Func.of(fn).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle pushIntegerMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushInteger", MemorySegment.class /* self */, long.class /* n */);

    public void pushInteger(long n) {
        try {
            pushIntegerMH.invokeExact(MEMORY, n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle pushLightUserDataMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushLightUserData", MemorySegment.class /* self */, MemorySegment.class /* p */);

    public void pushLightUserData(MemorySegment p) {
        try {
            pushLightUserDataMH.invokeExact(MEMORY, (MemorySegment) (p == null ? MemorySegment.NULL : p));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle pushLStringMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushLString", MemorySegment.class /* self */, String.class /* s */, long.class /* len */);

    public void pushLString(PNIString s, long len) {
        try {
            pushLStringMH.invokeExact(MEMORY, (MemorySegment) (s == null ? MemorySegment.NULL : s.MEMORY), len);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle pushNilMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushNil", MemorySegment.class /* self */);

    public void pushNil() {
        try {
            pushNilMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle pushNumberMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushNumber", MemorySegment.class /* self */, double.class /* n */);

    public void pushNumber(double n) {
        try {
            pushNumberMH.invokeExact(MEMORY, n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle pushStringMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushString", MemorySegment.class /* self */, String.class /* s */);

    public void pushString(PNIString s) {
        try {
            pushStringMH.invokeExact(MEMORY, (MemorySegment) (s == null ? MemorySegment.NULL : s.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle pushThreadMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushThread", MemorySegment.class /* self */);

    public int pushThread() {
        int RESULT;
        try {
            RESULT = (int) pushThreadMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle pushUserDataMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushUserData", MemorySegment.class /* self */, MemorySegment.class /* data */, long.class /* size */, String.class /* metatableName */);

    public void pushUserData(MemorySegment data, long size, PNIString metatableName) {
        try {
            pushUserDataMH.invokeExact(MEMORY, (MemorySegment) (data == null ? MemorySegment.NULL : data), size, (MemorySegment) (metatableName == null ? MemorySegment.NULL : metatableName.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle pushValueMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_pushValue", MemorySegment.class /* self */, int.class /* index */);

    public void pushValue(int index) {
        try {
            pushValueMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle rawEqualMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_rawEqual", MemorySegment.class /* self */, int.class /* idx1 */, int.class /* idx2 */);

    public boolean rawEqual(int idx1, int idx2) {
        boolean RESULT;
        try {
            RESULT = (boolean) rawEqualMH.invokeExact(MEMORY, idx1, idx2);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle rawGetMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_rawGet", MemorySegment.class /* self */, int.class /* index */);

    public void rawGet(int index) {
        try {
            rawGetMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle rawGetIMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_rawGetI", MemorySegment.class /* self */, int.class /* index */, int.class /* n */);

    public void rawGetI(int index, int n) {
        try {
            rawGetIMH.invokeExact(MEMORY, index, n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle rawSetMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_rawSet", MemorySegment.class /* self */, int.class /* index */);

    public void rawSet(int index) {
        try {
            rawSetMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle rawSetIMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_rawSetI", MemorySegment.class /* self */, int.class /* index */, int.class /* n */);

    public void rawSetI(int index, int n) {
        try {
            rawSetIMH.invokeExact(MEMORY, index, n);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle removeMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_remove", MemorySegment.class /* self */, int.class /* index */);

    public void remove(int index) {
        try {
            removeMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle replaceMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_replace", MemorySegment.class /* self */, int.class /* index */);

    public void replace(int index) {
        try {
            replaceMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle setFEnvMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_LuaState_setFEnv", MemorySegment.class /* self */, int.class /* index */);

    public int setFEnv(int index) {
        int RESULT;
        try {
            RESULT = (int) setFEnvMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle setFieldMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_setField", MemorySegment.class /* self */, int.class /* index */, String.class /* k */);

    public void setField(int index, PNIString k) {
        try {
            setFieldMH.invokeExact(MEMORY, index, (MemorySegment) (k == null ? MemorySegment.NULL : k.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle setGlobalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_setGlobal", MemorySegment.class /* self */, String.class /* name */);

    public void setGlobal(PNIString name) {
        try {
            setGlobalMH.invokeExact(MEMORY, (MemorySegment) (name == null ? MemorySegment.NULL : name.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle setMetaTableMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_LuaState_setMetaTable", MemorySegment.class /* self */, int.class /* index */);

    public int setMetaTable(int index) {
        int RESULT;
        try {
            RESULT = (int) setMetaTableMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle setTableMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_setTable", MemorySegment.class /* self */, int.class /* index */);

    public void setTable(int index) {
        try {
            setTableMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle setTopMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), void.class, "JavaCritical_io_vproxy_luajn_n_LuaState_setTop", MemorySegment.class /* self */, int.class /* index */);

    public void setTop(int index) {
        try {
            setTopMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    private static final MethodHandle strLenMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_LuaState_strLen", MemorySegment.class /* self */, int.class /* index */);

    public int strLen(int index) {
        int RESULT;
        try {
            RESULT = (int) strLenMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle toBooleanMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), boolean.class, "JavaCritical_io_vproxy_luajn_n_LuaState_toBoolean", MemorySegment.class /* self */, int.class /* index */);

    public boolean toBoolean(int index) {
        boolean RESULT;
        try {
            RESULT = (boolean) toBooleanMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle toCFunctionMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), MemorySegment.class, "JavaCritical_io_vproxy_luajn_n_LuaState_toCFunction", MemorySegment.class /* self */, int.class /* index */);

    public MemorySegment toCFunction(int index) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) toCFunctionMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle toIntegerMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), long.class, "JavaCritical_io_vproxy_luajn_n_LuaState_toInteger", MemorySegment.class /* self */, int.class /* index */);

    public long toInteger(int index) {
        long RESULT;
        try {
            RESULT = (long) toIntegerMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle toLStringMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), String.class, "JavaCritical_io_vproxy_luajn_n_LuaState_toLString", MemorySegment.class /* self */, int.class /* index */, MemorySegment.class /* len */);

    public PNIString toLString(int index, LongArray len) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) toLStringMH.invokeExact(MEMORY, index, (MemorySegment) (len == null ? MemorySegment.NULL : len.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }

    private static final MethodHandle toNumberMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), double.class, "JavaCritical_io_vproxy_luajn_n_LuaState_toNumber", MemorySegment.class /* self */, int.class /* index */);

    public double toNumber(int index) {
        double RESULT;
        try {
            RESULT = (double) toNumberMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle toPointerMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), MemorySegment.class, "JavaCritical_io_vproxy_luajn_n_LuaState_toPointer", MemorySegment.class /* self */, int.class /* index */);

    public MemorySegment toPointer(int index) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) toPointerMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle toStringMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), String.class, "JavaCritical_io_vproxy_luajn_n_LuaState_toString", MemorySegment.class /* self */, int.class /* index */);

    public PNIString toString(int index) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) toStringMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }

    private static final MethodHandle toThreadMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), io.vproxy.luajn.n.LuaState.LAYOUT.getClass(), "JavaCritical_io_vproxy_luajn_n_LuaState_toThread", MemorySegment.class /* self */, int.class /* index */, MemorySegment.class /* return */);

    public io.vproxy.luajn.n.LuaState toThread(int index, Allocator ALLOCATOR) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) toThreadMH.invokeExact(MEMORY, index, ALLOCATOR.allocate(io.vproxy.luajn.n.LuaState.LAYOUT));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT == null ? null : new io.vproxy.luajn.n.LuaState(RESULT);
    }

    private static final MethodHandle toUserDataMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), MemorySegment.class, "JavaCritical_io_vproxy_luajn_n_LuaState_toUserData", MemorySegment.class /* self */, int.class /* index */);

    public MemorySegment toUserData(int index) {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) toUserDataMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle typeMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions().setCritical(true), int.class, "JavaCritical_io_vproxy_luajn_n_LuaState_type", MemorySegment.class /* self */, int.class /* index */);

    public int type(int index) {
        int RESULT;
        try {
            RESULT = (int) typeMH.invokeExact(MEMORY, index);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("LuaState{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("L => ");
            SB.append(PanamaUtils.memorySegmentToString(getL()));
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<LuaState> {
        public Array(MemorySegment buf) {
            super(buf, LuaState.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, LuaState.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, LuaState.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.luajn.n.LuaState ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "LuaState.Array";
        }

        @Override
        protected LuaState construct(MemorySegment seg) {
            return new LuaState(seg);
        }

        @Override
        protected MemorySegment getSegment(LuaState value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<LuaState> {
        private Func(io.vproxy.pni.CallSite<LuaState> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<LuaState> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<LuaState> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<LuaState> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "LuaState.Func";
        }

        @Override
        protected LuaState construct(MemorySegment seg) {
            return new LuaState(seg);
        }
    }
}
// metadata.generator-version: pni 21.0.0.17
// sha256:d0f8402ab343ce560e2f8b32c6a5f32b5031b19a78823f2e39a8a7dabcaf2643
