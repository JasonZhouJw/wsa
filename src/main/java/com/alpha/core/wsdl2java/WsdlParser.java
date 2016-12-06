package com.alpha.core.wsdl2java;

import com.alpha.core.common.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.*;

/**
 * Created by jzhou237 on 2016-12-01.
 */
@Slf4j
@Component
public class WsdlParser {

    //    @Value("${cxf.executor}")
    private String executor;

    //    @Value("${cxf.javaPath}")
    private String javaPath;

    //    @Value("${cxf.compilerPath}")
    private String compilerPath;

    public void execute(String customPackage, String wsdl) {
        this.generateSourceFile(wsdl);
        this.compilerJava();
    }

    private void compilerJava() {
        File srcFile = new File(this.javaPath);
        if (srcFile.isDirectory()) {
            Map<String, List<File>> fileMap = new HashMap<String, List<File>>();
            fileMap.put("", Arrays.asList(srcFile.listFiles()));
            this.compilerJava(fileMap);
        }
    }

    private void compilerJava(Map<String, List<File>> fileMap) {
        if (fileMap == null || fileMap.size() == 0) {
            return;
        }
        Map<String, List<File>> childFileMap = new HashMap<String, List<File>>();
        fileMap.forEach((packageName, files) -> {
            List<File> failFiles = new ArrayList<File>();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".java")) {
                    boolean result = false;
//                    result = ToolProvider.getSystemJavaCompiler().run(null, null, null, "-d", this.compilerPath, file.getPath());
                    JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
                    StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);
                    Iterable iterable = standardJavaFileManager.getJavaFileObjects(file);
                    JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardJavaFileManager, null, Arrays.asList("-classpath", this.compilerPath, "-d", this.compilerPath), null, iterable);
                    result = task.call();
                    try {
                        standardJavaFileManager.close();
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                    }
                    if (result) {
                        try {
                            String className = packageName + Constants.DOT + file.getName().substring(0, file.getName().lastIndexOf(".java"));
                            CustomClassLoad customClassLoad = new CustomClassLoad(this.compilerPath, new String[]{className});
                            customClassLoad.loadClass(className);
                        } catch (ClassNotFoundException e) {
                            log.error(e.getMessage(), e);
                            failFiles.add(file);
                        }
                        continue;
                    } else {
                        failFiles.add(file);
                    }
                } else if (file.isDirectory()) {
                    String parentPackageName = StringUtils.isBlank(packageName) ? "" : packageName + Constants.DOT;
                    childFileMap.put(parentPackageName + file.getName(), Arrays.asList(file.listFiles()));
                }
            }
            if (CollectionUtils.isNotEmpty(failFiles)) {
                childFileMap.put(packageName, failFiles);
            }
        });
        compilerJava(childFileMap);
    }

    private void generateSourceFile(String wsdl) {
        Runtime runtime = Runtime.getRuntime();
        try {
            String command = this.executor + " -d " + this.javaPath + " " + wsdl;
            Process process = runtime.exec(command);
            BufferedInputStream in = new BufferedInputStream(process.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                System.out.println(lineStr);
            }
            if (process.waitFor() != 0 && process.exitValue() == 1) {
                System.err.println("Error!");
            }
            inBr.close();
            in.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public void setJavaPath(String javaPath) {
        this.javaPath = javaPath;
    }

    public void setCompilerPath(String compilerPath) {
        this.compilerPath = compilerPath;
    }
}
