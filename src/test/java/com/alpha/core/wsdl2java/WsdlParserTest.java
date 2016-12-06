package com.alpha.core.wsdl2java;

import org.junit.Test;

/**
 * Created by jzhou237 on 2016-12-01.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class WsdlParserTest {

    //    @Autowired
    private WsdlParser wsdlParser = new WsdlParser();

    @Test
    public void execute() throws Exception {
        wsdlParser.setCompilerPath("c:/tmp/classes/");
        wsdlParser.setExecutor("c:/dev/apache-cxf-3.1.8/bin/wadl2java.bat");
        wsdlParser.setJavaPath("c:/tmp/src/");
        wsdlParser.execute(null, "http://localhost:8080/services/hello?wsdl");
    }

}