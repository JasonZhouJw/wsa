package com.alpha.services.threads;

import com.alpha.loader.ClassCache;
import com.alpha.loader.ServicesLoader;
import com.alpha.loader.entities.ServicesClass;
import com.alpha.services.domain.IServiceInfo;
import com.alpha.services.entities.ServiceInfo;
import com.alpha.wsdl2java.Wsdl2JavaGenerator;
import com.alpha.wsdl2java.WsdlClassCompiler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jzhou237 on 2016-09-30.
 */
@Slf4j
public class AssembleWsdlRunnable implements Runnable {

    @Getter
    private String name;

    private ServiceInfo assembleServiceInfo;

    private WsdlClassCompiler wsdlClassCompiler;

    private Wsdl2JavaGenerator wsdl2JavaGenerator;

    private IServiceInfo serviceInfo;

    public AssembleWsdlRunnable(ServiceInfo assembleServiceInfo, WsdlClassCompiler wsdlClassCompiler, Wsdl2JavaGenerator wsdl2JavaGenerator, IServiceInfo serviceInfo) {
        this.name = UUID.randomUUID().toString();
        this.assembleServiceInfo = assembleServiceInfo;
        this.wsdl2JavaGenerator = wsdl2JavaGenerator;
        this.wsdlClassCompiler = wsdlClassCompiler;
        this.serviceInfo = serviceInfo;
    }

    @Override
    public void run() {
        log.info("Thread [" + this.name + "] start.");
        try {
            this.wsdl2JavaGenerator.execute(assembleServiceInfo.getWsdl());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        this.wsdlClassCompiler.compile();
        //analysis class information after compiled
        this.analysisClass();

        ServicesClass servicesClass = ServicesLoader.getInstance().get(assembleServiceInfo.getWsdl());
        if (servicesClass != null) {
            this.serviceInfo.assemble(this.assembleServiceInfo.getId(), servicesClass.getServiceName());
        }
        log.info("Thread [" + this.name + "] end.");
    }

    private void analysisClass() {
        Map<String, Class> classMap = ClassCache.getInstance().getClassData();
        classMap.forEach((className, clazz) -> {
            ServicesLoader.getInstance().put(clazz);
        });
    }

}
