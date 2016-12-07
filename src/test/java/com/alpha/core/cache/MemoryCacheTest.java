package com.alpha.core.cache;

import com.alpha.core.cache.model.TypeData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by jzhou237 on 2016-11-09.
 */
public class MemoryCacheTest {

    @Test
    public void getInstance() throws Exception {
        Thread t1 = new Thread(new BaseThread("T1"));
        Thread t2 = new Thread(new BaseThread("T2"));
        Thread t3 = new Thread(new BaseThread("T3"));
        t1.start();
        t2.start();
        t3.start();
    }

    @Test
    public void init() throws Exception {
        TypeData typeData = MemoryCache.getInstance().init("Test");
        TypeData typeData1 = MemoryCache.getInstance().init("Test");
        assertEquals(typeData.hashCode(), typeData1.hashCode());
        assertNotEquals(typeData.hashCode(), MemoryCache.getInstance().init("DEV"));
    }

    @Test
    public void putAndGet() throws Exception {
        MemoryCache.getInstance().put("TEST", "key", "value");
        assertEquals("value", MemoryCache.getInstance().get("TEST", "key"));
        assertEquals(1, 1);
    }

    @Test
    public void refresh() throws Exception {

    }

    private class BaseThread implements Runnable {

        private MemoryCache memoryCache = MemoryCache.getInstance();

        private String name;

        public BaseThread(String name) {
            this.name = name;
        }


        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                if (memoryCache.hashCode() != MemoryCache.getInstance().hashCode()) {
                    System.out.println("Thread [" + this.name + "] HashCode is not equals");
                }
            }
        }

        public MemoryCache getMemoryCache() {
            return memoryCache;
        }
    }

}