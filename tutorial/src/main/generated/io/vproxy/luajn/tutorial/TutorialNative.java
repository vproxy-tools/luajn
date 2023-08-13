package io.vproxy.luajn.tutorial;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class TutorialNative {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("intValue"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG_UNALIGNED.withName("longValue")
    );
    public final MemorySegment MEMORY;

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
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    private static final MethodHandle dummyPlaceHolderForGeneratingImplHFileMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_tutorial_TutorialNative_dummyPlaceHolderForGeneratingImplHFile", MemorySegment.class /* self */);

    public void dummyPlaceHolderForGeneratingImplHFile() {
        try {
            dummyPlaceHolderForGeneratingImplHFileMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
    }

    public static class Array extends RefArray<TutorialNative> {
        public Array(MemorySegment buf) {
            super(buf, TutorialNative.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(TutorialNative.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
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

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<TutorialNative> func) {
            return new Func(func);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected TutorialNative construct(MemorySegment seg) {
            return new TutorialNative(seg);
        }
    }
}
// metadata.generator-version: pni 21.0.0.8
// sha256:97b9f307bbd1360b2e5eaa800267e5846e2c551287c32633652e2a1ec2a75d83
