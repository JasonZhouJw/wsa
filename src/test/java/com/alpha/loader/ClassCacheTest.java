package com.alpha.loader;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jzhou237 on 2017-04-12.
 */
public class ClassCacheTest {

    private String[] classNames = {"com.alpha.core.ws.server.HelloImplService", "com.alpha.core.ws.server.IHello",
            "com.alpha.core.ws.server.SayHello", "com.alpha.core.ws.server.SayHelloResponse"};

    @Test
    public void loadClass() throws Exception {
        CustomClassLoad customClassLoad = new CustomClassLoad("c:/tmp/classes/");
        for (String name : classNames) {
            ClassCache.getInstance().loadClass(customClassLoad, name);
        }
        Class clazz = ClassCache.getInstance().getClass("com.alpha.core.ws.server.HelloImplService");
        assertNotNull(clazz);
        assertNotNull(clazz.getMethods());
    }

    @Test
    public void loadClass_twice() throws Exception {
        CustomClassLoad customClassLoad = new CustomClassLoad("c:/tmp/classes/");
        for (String name : classNames) {
            ClassCache.getInstance().loadClass(customClassLoad, name);
        }
        Class clazz = ClassCache.getInstance().getClass("com.alpha.core.ws.server.HelloImplService");
        assertNotNull(clazz);
        assertNotNull(clazz.getMethods());

        customClassLoad = new CustomClassLoad("c:/tmp/classes/");
        for (String name : classNames) {
            ClassCache.getInstance().loadClass(customClassLoad, name);
        }
        clazz = ClassCache.getInstance().getClass("com.alpha.core.ws.server.HelloImplService");
        assertNotNull(clazz);
        assertNotNull(clazz.getMethods());
    }

    @Test
    public void testgetClass() throws Exception {

    }

}