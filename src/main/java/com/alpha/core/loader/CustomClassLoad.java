package com.alpha.core.loader;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jzhou237 on 2016-12-02.
 */
@Slf4j
public class CustomClassLoad extends ClassLoader {

    private String basedir;

    public CustomClassLoad(String basedir, String name) throws ClassNotFoundException {
        super(null);
        this.basedir = basedir;
        loadCustomClass(name);
    }

    public CustomClassLoad(String basedir) {
        super(null);
        this.basedir = basedir;
    }

    public Class loadCustomClass(String name) throws ClassNotFoundException {
        try {
            return loadDirectly(name);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }

    private Class loadDirectly(String name) throws IOException {
        StringBuffer sb = new StringBuffer(basedir);
        String classname = name.replace('.', File.separatorChar) + ".class";
        sb.append(File.separator + classname);
        File classF = new File(sb.toString());
        @Cleanup InputStream is = new FileInputStream(classF);
        byte[] raw = new byte[(int) classF.length()];
        is.read(raw);
        return defineClass(name, raw, 0, raw.length);
    }

    protected Class loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        Class cls = ClassCache.getInstance().getClass(name);
        if (cls == null) {
            cls = super.loadClass(name, resolve);
        }
        return cls;
    }
}
