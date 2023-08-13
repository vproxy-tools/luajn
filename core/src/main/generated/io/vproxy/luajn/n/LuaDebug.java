package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class LuaDebug {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("event"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.ADDRESS_UNALIGNED.withName("name"),
        ValueLayout.ADDRESS_UNALIGNED.withName("nameWhat"),
        ValueLayout.ADDRESS_UNALIGNED.withName("what"),
        ValueLayout.ADDRESS_UNALIGNED.withName("source"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("currentLine"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("nUps"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("lineDefined"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("lastLineDefined"),
        MemoryLayout.sequenceLayout(60L, ValueLayout.JAVA_BYTE).withName("shortSrc"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    private static final VarHandle eventVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("event")
    );

    public int getEvent() {
        return (int) eventVH.get(MEMORY);
    }

    public void setEvent(int event) {
        eventVH.set(MEMORY, event);
    }

    private static final VarHandle nameVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("name")
    );

    public PNIString getName() {
        var SEG = (MemorySegment) nameVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return new PNIString(SEG);
    }

    public void setName(String name, Allocator ALLOCATOR) {
        this.setName(new PNIString(ALLOCATOR, name));
    }

    public void setName(PNIString name) {
        if (name == null) {
            nameVH.set(MEMORY, MemorySegment.NULL);
        } else {
            nameVH.set(MEMORY, name.MEMORY);
        }
    }

    private static final VarHandle nameWhatVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("nameWhat")
    );

    public PNIString getNameWhat() {
        var SEG = (MemorySegment) nameWhatVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return new PNIString(SEG);
    }

    public void setNameWhat(String nameWhat, Allocator ALLOCATOR) {
        this.setNameWhat(new PNIString(ALLOCATOR, nameWhat));
    }

    public void setNameWhat(PNIString nameWhat) {
        if (nameWhat == null) {
            nameWhatVH.set(MEMORY, MemorySegment.NULL);
        } else {
            nameWhatVH.set(MEMORY, nameWhat.MEMORY);
        }
    }

    private static final VarHandle whatVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("what")
    );

    public PNIString getWhat() {
        var SEG = (MemorySegment) whatVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return new PNIString(SEG);
    }

    public void setWhat(String what, Allocator ALLOCATOR) {
        this.setWhat(new PNIString(ALLOCATOR, what));
    }

    public void setWhat(PNIString what) {
        if (what == null) {
            whatVH.set(MEMORY, MemorySegment.NULL);
        } else {
            whatVH.set(MEMORY, what.MEMORY);
        }
    }

    private static final VarHandle sourceVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("source")
    );

    public PNIString getSource() {
        var SEG = (MemorySegment) sourceVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return new PNIString(SEG);
    }

    public void setSource(String source, Allocator ALLOCATOR) {
        this.setSource(new PNIString(ALLOCATOR, source));
    }

    public void setSource(PNIString source) {
        if (source == null) {
            sourceVH.set(MEMORY, MemorySegment.NULL);
        } else {
            sourceVH.set(MEMORY, source.MEMORY);
        }
    }

    private static final VarHandle currentLineVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("currentLine")
    );

    public int getCurrentLine() {
        return (int) currentLineVH.get(MEMORY);
    }

    public void setCurrentLine(int currentLine) {
        currentLineVH.set(MEMORY, currentLine);
    }

    private static final VarHandle nUpsVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("nUps")
    );

    public int getNUps() {
        return (int) nUpsVH.get(MEMORY);
    }

    public void setNUps(int nUps) {
        nUpsVH.set(MEMORY, nUps);
    }

    private static final VarHandle lineDefinedVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("lineDefined")
    );

    public int getLineDefined() {
        return (int) lineDefinedVH.get(MEMORY);
    }

    public void setLineDefined(int lineDefined) {
        lineDefinedVH.set(MEMORY, lineDefined);
    }

    private static final VarHandle lastLineDefinedVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("lastLineDefined")
    );

    public int getLastLineDefined() {
        return (int) lastLineDefinedVH.get(MEMORY);
    }

    public void setLastLineDefined(int lastLineDefined) {
        lastLineDefinedVH.set(MEMORY, lastLineDefined);
    }

    private final MemorySegment shortSrc;

    public MemorySegment getShortSrc() {
        return this.shortSrc;
    }

    public LuaDebug(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += 8;
        OFFSET += 8;
        OFFSET += 8;
        OFFSET += 8;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        this.shortSrc = MEMORY.asSlice(OFFSET, 60);
        OFFSET += 60;
        OFFSET += 4; /* padding */
    }

    public LuaDebug(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<LuaDebug> {
        public Array(MemorySegment buf) {
            super(buf, LuaDebug.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(LuaDebug.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected LuaDebug construct(MemorySegment seg) {
            return new LuaDebug(seg);
        }

        @Override
        protected MemorySegment getSegment(LuaDebug value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<LuaDebug> {
        private Func(io.vproxy.pni.CallSite<LuaDebug> func) {
            super(func);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<LuaDebug> func) {
            return new Func(func);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected LuaDebug construct(MemorySegment seg) {
            return new LuaDebug(seg);
        }
    }
}
// metadata.generator-version: pni 21.0.0.8
// sha256:16eb9c5da3e2871e27fd0beca04c05184e8d483566d104ca54f8b886c2ef0c1a
