package io.vproxy.luajn.benchmark;

public class BenchmarkUpcallImpl implements BenchmarkUpcall.Interface {
    private BenchmarkUpcallImpl() {
    }

    private static final BenchmarkUpcallImpl IMPL = new BenchmarkUpcallImpl();

    public static BenchmarkUpcall.Interface get() {
        return IMPL;
    }

    @Override
    public int oneIntArgIntReturn(int a) {
        return a;
    }

    @Override
    public int twoIntArgIntReturn(int a, int b) {
        return a + b;
    }

    @Override
    public int oneRefArgIntReturn(Integer ref) {
        return ref;
    }
}
