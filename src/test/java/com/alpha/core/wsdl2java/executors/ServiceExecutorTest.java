package com.alpha.core.wsdl2java.executors;

import com.alpha.core.wsdl2java.WsdlClassCompiler;
import com.alpha.core.wsdl2java.entities.ServiceMethod;
import com.alpha.core.wsdl2java.entities.Services;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * Created by jzhou237 on 2016-12-09.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceExecutorTest {

    @Autowired
    private WsdlClassCompiler wsdlClassCompiler;


    @Test
    public void execute() throws Exception {
//        WsdlClassCompiler wsdlClassCompiler=new WsdlClassCompiler();
//        wsdlClassCompiler.setCompilerPath("c:/tmp/classes/");
//        wsdlClassCompiler.setJavaPath("c:/tmp/src/");
        wsdlClassCompiler.compile();
        ServiceExecutor serviceExecutor = new ServiceExecutor();
        Services services = new Services();
        services.setInterfaceClass("com.alpha.core.server.IHello");
        services.setServicesName("com.alpha.core.server.HelloImplService");
        services.setMethodArgumentsMap(new HashMap<String, ServiceMethod>());
        ServiceMethod serviceMethod = new ServiceMethod();
        serviceMethod.setArguments(Arrays.asList(new String[]{"com.alpha.core.server.entity.User"}));
        serviceMethod.setReturnClass("com.alpha.core.server.entity.User");
        serviceMethod.setMethod("say");
        services.getMethodArgumentsMap().put("say", serviceMethod);
        serviceExecutor.execute(services, "say");
    }

}