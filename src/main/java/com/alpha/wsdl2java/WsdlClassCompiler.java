package com.alpha.wsdl2java;

import com.alpha.common.utils.Constants;
import com.alpha.loader.ClassCache;
import com.alpha.loader.ServicesLoader;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by jzhou237 on 2016-12-08.
 */
@Slf4j
@Setter
@Component
public class WsdlClassCompiler {

    //    @Value("${cxf.javaPath}")
    private String javaPath;

    //    @Value("${cxf.compilerPath}")
    private String compilerPath;

    public void compile() {
        File srcFile = new File(this.javaPath);
        if (srcFile.isDirectory()) {
            Map<String, List<File>> fileMap = new HashMap<String, List<File>>();
            fileMap.put("", Arrays.asList(srcFile.listFiles()));
            this.compile(fileMap);
        } else {
            log.warn(this.javaPath + " is not a directory.");
        }
    }

    private void compile(Map<String, List<File>> fileMap) {
        if (fileMap == null || fileMap.size() == 0) {
            return;
        }
        Map<String, List<File>> childFileMap = new HashMap<String, List<File>>();
        fileMap.forEach((packageName, files) -> {
            List<File> failFiles = new ArrayList<File>();
            List<File> javaFiles = new ArrayList<File>();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".java")) {
                    javaFiles.add(file);
                } else if (file.isDirectory()) {
                    String parentPackageName = StringUtils.isBlank(packageName) ? "" : packageName + Constants.DOT;
                    childFileMap.put(parentPackageName + file.getName(), Arrays.asList(file.listFiles()));
                }
            }
            if (CollectionUtils.isNotEmpty(javaFiles)) {
                boolean result = false;
                JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
                StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);
                Iterable iterable = standardJavaFileManager.getJavaFileObjectsFromFiles(javaFiles);
                JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardJavaFileManager, null, Arrays.asList("-classpath", this.compilerPath, "-d", this.compilerPath), null, iterable);
                result = task.call();
                try {
                    standardJavaFileManager.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
                if (result) {
                    loadClass(packageName, javaFiles);
                }
            }
        });
        compile(childFileMap);
    }

    private void loadClass(String packageName, List<File> files) {
        files.forEach(file -> {
            String className = packageName + Constants.DOT + file.getName().substring(0, file.getName().lastIndexOf(".java"));
            try {
                ServicesLoader.getInstance().put(ClassCache.getInstance().loadClass(className));
            } catch (ClassNotFoundException e) {
                log.error("Fail to Load " + className, e);
            }
        });
    }
}
