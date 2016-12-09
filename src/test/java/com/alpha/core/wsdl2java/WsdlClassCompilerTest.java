package com.alpha.core.wsdl2java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jzhou237 on 2016-12-08.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WsdlClassCompilerTest {

    @Autowired
    private WsdlClassCompiler wsdlClassCompiler;

    @Test
    public void compile() throws Exception {
        wsdlClassCompiler.compile();
    }

}