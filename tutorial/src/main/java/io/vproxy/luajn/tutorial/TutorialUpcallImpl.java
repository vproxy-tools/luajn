package io.vproxy.luajn.tutorial;

import io.vproxy.pni.PNIString;

import java.util.List;

public class TutorialUpcallImpl implements TutorialUpcall.Interface {
    private TutorialUpcallImpl() {
    }

    private static final TutorialUpcallImpl IMPL = new TutorialUpcallImpl();

    public static TutorialUpcall.Interface get() {
        return IMPL;
    }

    @Override
    public void add(List<String> ls, PNIString str) {
        ls.add(str.toString());
    }

    @Override
    public void remove(List<String> ls, int index) {
        ls.remove(index);
    }

    @Override
    public int size(List<String> ls) {
        return ls.size();
    }
}
