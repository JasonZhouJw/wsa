package com.alpha.wsdl2java;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jzhou237 on 2016-12-01.
 */
@Slf4j
@Component
//@PropertySource(SYS_PROPERTIES)
public class Wsdl2JavaGenerator {

    //    @Value("${cxf.executor}")
    private String executor;

    //    @Value("${cxf.javaPath}")
    private String javaPath;

    public void execute(String wsdl) throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        String command = this.executor + " -d " + this.javaPath + " " + wsdl;
        Process process = runtime.exec(command);
        @Cleanup BufferedInputStream in = new BufferedInputStream(process.getInputStream());
        @Cleanup BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
        String lineStr;
        while ((lineStr = inBr.readLine()) != null) {
            log.info(lineStr);
        }
        if (process.waitFor() != 0 && process.exitValue() == 1) {
            log.error("Fail to generate java file by wsdl. WSDL: " + wsdl);
        }
    }

}
