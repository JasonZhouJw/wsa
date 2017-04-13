package com.alpha.loader;

import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jzhou237 on 2016-12-02.
 */
@Getter
@Setter
@Slf4j
public class CustomClassLoad extends ClassLoader {

    private String basedir;

    private Set<String> classNameSet = new HashSet<>();

    public CustomClassLoad(String basedir) {
        super(null);
        this.basedir = basedir;
    }


    public Class loadCustomClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        try {
            clazz = loadDirectly(name);
            classNameSet.add(name);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ClassNotFoundException();
        }
        return clazz;
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
        Class cls = null;
        cls = findLoadedClass(name);
        if (!this.classNameSet.contains(name) && cls == null) {
            cls = getSystemClassLoader().loadClass(name);
        }
        if (cls == null) {
            throw new ClassNotFoundException(name);
        }
        if (resolve) {
            resolveClass(cls);
        }
        return cls;
    }
}
