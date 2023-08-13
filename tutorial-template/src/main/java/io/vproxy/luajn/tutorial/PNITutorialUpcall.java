package io.vproxy.luajn.tutorial;

import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.Name;
import io.vproxy.pni.annotation.Upcall;

import java.util.List;

@Upcall
public interface PNITutorialUpcall {
    @Name("luajn_tutorial_ls_add")
    void add(PNIRef<List<String>> ls, String str);

    @Name("luajn_tutorial_ls_remove")
    void remove(PNIRef<List<String>> ls, int index);

    @Name("luajn_tutorial_ls_size")
    int size(PNIRef<List<String>> ls);
}
