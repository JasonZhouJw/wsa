package com.alpha.core.wsdl2java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jzhou237 on 2016-12-01.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WsdlParserTest {

    @Autowired
    private WsdlParser wsdlParser = new WsdlParser();

    @Test
    public void execute() throws Exception {
        wsdlParser.execute(null, "http://localhost:8080/services/hello?wsdl");
    }

}