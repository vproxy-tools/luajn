package io.vproxy.luajn.benchmark;

import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.Upcall;

@Upcall
public interface PNIBenchmarkUpcall {
    int oneIntArgIntReturn(int a);

    int twoIntArgIntReturn(int a, int b);

    int oneRefArgIntReturn(PNIRef<Integer> ref);
}
