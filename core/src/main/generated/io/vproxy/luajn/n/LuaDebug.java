package io.vproxy.luajn.n;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class LuaDebug extends AbstractNativeObject implements NativeObject {
    private static final MethodHandle __getLayoutByteSizeMH = PanamaUtils.lookupPNICriticalFunction(true, long.class, "JavaCritical_io_vproxy_luajn_n_LuaDebug___getLayoutByteSize");

    private static long __getLayoutByteSize() {
        long RESULT;
        try {
            RESULT = (long) __getLayoutByteSizeMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    public static final MemoryLayout LAYOUT = PanamaUtils.padLayout(__getLayoutByteSize(), MemoryLayout::structLayout,
        ValueLayout.JAVA_INT.withName("event"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.ADDRESS.withName("name"),
        ValueLayout.ADDRESS.withName("nameWhat"),
        ValueLayout.ADDRESS.withName("what"),
        ValueLayout.ADDRESS.withName("source"),
        ValueLayout.JAVA_INT.withName("currentLine"),
        ValueLayout.JAVA_INT.withName("nUps"),
        ValueLayout.JAVA_INT.withName("lineDefined"),
        ValueLayout.JAVA_INT.withName("lastLineDefined"),
        MemoryLayout.sequenceLayout(60L, ValueLayout.JAVA_BYTE).withName("shortSrc"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */
    ).withByteAlignment(8);
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

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
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("LuaDebug{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("event => ");
            SB.append(getEvent());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("name => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getName(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("nameWhat => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getNameWhat(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("what => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getWhat(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("source => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getSource(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("currentLine => ");
            SB.append(getCurrentLine());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("nUps => ");
            SB.append(getNUps());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("lineDefined => ");
            SB.append(getLineDefined());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("lastLineDefined => ");
            SB.append(getLastLineDefined());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("shortSrc => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else SB.append(PanamaUtils.memorySegmentToString(getShortSrc()));
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<LuaDebug> {
        public Array(MemorySegment buf) {
            super(buf, LuaDebug.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, LuaDebug.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, LuaDebug.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.luajn.n.LuaDebug ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "LuaDebug.Array";
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

        private Func(io.vproxy.pni.CallSite<LuaDebug> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<LuaDebug> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<LuaDebug> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "LuaDebug.Func";
        }

        @Override
        protected LuaDebug construct(MemorySegment seg) {
            return new LuaDebug(seg);
        }
    }
}
// metadata.generator-version: pni 21.0.0.15
// sha256:df4c8255bb966fc5b422a127c84648b54e002faa8e1d01cd5389ddded1342308
