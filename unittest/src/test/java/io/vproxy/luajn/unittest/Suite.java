package io.vproxy.luajn.unittest;

import org.junit.runner.RunWith;

@org.junit.runners.Suite.SuiteClasses({
    BeforeAll.class,
    TestLuaJNState.class,
    TestLuaJNTable.class,
    TestLuaJNFunction.class,
    TestLuaCoroutine.class,
    TestGC.class,
})
@RunWith(org.junit.runners.Suite.class)
public class Suite {
}
