package io.vproxy.luajn.tutorial;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class TutorialNative extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_INT.withName("intValue"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG.withName("longValue")
    ).withByteAlignment(8);
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle intValueVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("intValue")
    );

    public int getIntValue() {
        return (int) intValueVH.get(MEMORY);
    }

    public void setIntValue(int intValue) {
        intValueVH.set(MEMORY, intValue);
    }

    private static final VarHandle longValueVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("longValue")
    );

    public long getLongValue() {
        return (long) longValueVH.get(MEMORY);
    }

    public void setLongValue(long longValue) {
        longValueVH.set(MEMORY, longValue);
    }

    public TutorialNative(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
    }

    public TutorialNative(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle dummyPlaceHolderForGeneratingImplHFileMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_tutorial_TutorialNative_dummyPlaceHolderForGeneratingImplHFile", MemorySegment.class /* self */);

    public void dummyPlaceHolderForGeneratingImplHFile() {
        try {
            dummyPlaceHolderForGeneratingImplHFileMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("TutorialNative{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("intValue => ");
            SB.append(getIntValue());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("longValue => ");
            SB.append(getLongValue());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<TutorialNative> {
        public Array(MemorySegment buf) {
            super(buf, TutorialNative.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, TutorialNative.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, TutorialNative.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.luajn.tutorial.TutorialNative ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "TutorialNative.Array";
        }

        @Override
        protected TutorialNative construct(MemorySegment seg) {
            return new TutorialNative(seg);
        }

        @Override
        protected MemorySegment getSegment(TutorialNative value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<TutorialNative> {
        private Func(io.vproxy.pni.CallSite<TutorialNative> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<TutorialNative> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<TutorialNative> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<TutorialNative> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "TutorialNative.Func";
        }

        @Override
        protected TutorialNative construct(MemorySegment seg) {
            return new TutorialNative(seg);
        }
    }
}
// metadata.generator-version: pni 21.0.0.15
// sha256:c15775ada310a3cc7bc03e1f256238850c15c486ed5db867109d3b6cf4efe08c
