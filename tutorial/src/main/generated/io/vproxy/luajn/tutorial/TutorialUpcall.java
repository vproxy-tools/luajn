package io.vproxy.luajn.tutorial;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class TutorialUpcall {
    private static final Arena ARENA = Arena.ofShared();

    public static final MemorySegment add;

    private static void add(MemorySegment ls, MemorySegment str) {
        if (IMPL == null) {
            System.out.println("io.vproxy.luajn.tutorial.TutorialUpcall#add");
            System.exit(1);
        }
        IMPL.add(
            (ls.address() == 0 ? null : PNIRef.getRef(ls)),
            (str.address() == 0 ? null : new PNIString(str))
        );
    }

    public static final MemorySegment remove;

    private static void remove(MemorySegment ls, int index) {
        if (IMPL == null) {
            System.out.println("io.vproxy.luajn.tutorial.TutorialUpcall#remove");
            System.exit(1);
        }
        IMPL.remove(
            (ls.address() == 0 ? null : PNIRef.getRef(ls)),
            index
        );
    }

    public static final MemorySegment size;

    private static int size(MemorySegment ls) {
        if (IMPL == null) {
            System.out.println("io.vproxy.luajn.tutorial.TutorialUpcall#size");
            System.exit(1);
        }
        var RESULT = IMPL.size(
            (ls.address() == 0 ? null : PNIRef.getRef(ls))
        );
        return RESULT;
    }

    static {
        MethodHandle addMH;
        MethodHandle removeMH;
        MethodHandle sizeMH;
        try {
            addMH = MethodHandles.lookup().findStatic(io.vproxy.luajn.tutorial.TutorialUpcall.class, "add", MethodType.methodType(void.class, MemorySegment.class, MemorySegment.class));
            removeMH = MethodHandles.lookup().findStatic(io.vproxy.luajn.tutorial.TutorialUpcall.class, "remove", MethodType.methodType(void.class, MemorySegment.class, int.class));
            sizeMH = MethodHandles.lookup().findStatic(io.vproxy.luajn.tutorial.TutorialUpcall.class, "size", MethodType.methodType(int.class, MemorySegment.class));
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        add = PanamaUtils.defineCFunction(ARENA, addMH, void.class, MemorySegment.class, MemorySegment.class);
        remove = PanamaUtils.defineCFunction(ARENA, removeMH, void.class, MemorySegment.class, int.class);
        size = PanamaUtils.defineCFunction(ARENA, sizeMH, int.class, MemorySegment.class);

        var initMH = PanamaUtils.lookupPNICriticalFunction(true, void.class, "JavaCritical_io_vproxy_luajn_tutorial_TutorialUpcall_INIT", MemorySegment.class, MemorySegment.class, MemorySegment.class);
        try {
            initMH.invoke(add, remove, size);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private static Interface IMPL = null;

    public static void setImpl(Interface impl) {
        java.util.Objects.requireNonNull(impl);
        IMPL = impl;
    }

    public interface Interface {
        void add(java.util.List<java.lang.String> ls, PNIString str);

        void remove(java.util.List<java.lang.String> ls, int index);

        int size(java.util.List<java.lang.String> ls);
    }
}
// metadata.generator-version: pni 21.0.0.8
// sha256:e26c6f4f7213948b6aeb1b35b0d85d043055c03351163e627da891b3f727a496
