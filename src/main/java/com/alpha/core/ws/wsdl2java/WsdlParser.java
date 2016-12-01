package com.alpha.core.ws.wsdl2java;

import com.alpha.core.ws.utils.Constants;
import com.alpha.core.ws.utils.ILog;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;

/**
 * Created by jzhou237 on 2016-12-01.
 */
@Component
public class WsdlParser implements ILog {

    @Value("${cxf.executor}")
    private String executor;

    @Value("${cxf.javaPath}")
    private String javaPath;

    @Value("${cxf.customPackage}")
    private String customPackage;

    @Value("${cxf.compilerPath}")
    private String compilerPath;

    public void execute(String customPackage, String wsdl) {
        customPackage = (StringUtils.isNotBlank(customPackage) ? customPackage : this.customPackage);
//        this.generateSourceFile(customPackage, wsdl);
        this.compilerJava(customPackage);
    }

    private void compilerJava(String customPackage) {
        String srcPath = this.javaPath + customPackage.replace(Constants.DOT, File.separator);
        File srcFile = new File(srcPath);
        if (srcFile.isDirectory()) {
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            File[] files = srcFile.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".java")) {
                    System.out.println(file.getParent());
                    int result = javaCompiler.run(null, null, null, "-d", file.getParent(), file.getPath());
                    // TODO: 2016-12-01 remove the system.out and refactor compiler
                    System.out.println(result);
                    if (file.getName().indexOf("HolidayRequest") < 0) {
                        continue;
                    }
                    Runtime run = Runtime.getRuntime();
                    Process process = null;
                    try {
                        process = run.exec("java -cp  " + file.getPath() + "  com.services.HolidayRequest");
                        InputStream in = process.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String info = "";
                        while ((info = reader.readLine()) != null) {
                            System.out.println(info);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void generateSourceFile(String customPackage, String wsdl) {
        Runtime runtime = Runtime.getRuntime();
        try {
            String command = this.executor + " -d " + this.javaPath + " -p " + customPackage + " " + wsdl;
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
            LOGGER.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
