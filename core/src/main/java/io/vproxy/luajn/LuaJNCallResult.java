package io.vproxy.luajn;

public class LuaJNCallResult {
    public final int nresults;
    private final LuaJNState L;
    private final LuaJNCallResultConverter[] resultConverters;

    public LuaJNCallResult(int nresults, LuaJNState L) {
        this.nresults = nresults;
        this.L = L;
        this.resultConverters = new LuaJNCallResultConverter[nresults];
    }

    public interface LuaJNCallResultConverter {
        Object convert(LuaJNState L);
    }

    private int resIndex = 0;

    public LuaJNCallResult convert(LuaJNCallResultConverter f) {
        if (resIndex >= nresults) {
            throw new IllegalStateException("resIndex (" + resIndex + ") >= nresults (" + nresults + ")");
        }
        resultConverters[resIndex++] = f;
        return this;
    }

    public Object result() {
        if (resIndex != nresults) {
            throw new IllegalStateException("resIndex (" + resIndex + ") != nresults (" + nresults + ")");
        }
        if (nresults == 0) {
            return null;
        }
        if (nresults == 1) {
            return convert(0);
        }
        var results = new Object[nresults];
        for (int i = 0; i < nresults; ++i) {
            int index = nresults - i - 1;
            results[index] = convert(index);
        }
        return results;
    }

    private Object convert(int index) {
        int before = L.getLuaState().getTop();
        var obj = resultConverters[index].convert(L);
        int after = L.getLuaState().getTop();
        if (after == before) {
            L.getLuaState().pop(1);
        } else if (after != before - 1) {
            throw new IllegalStateException("the converter modified the stack unexpectedly");
        }
        return obj;
    }
}
