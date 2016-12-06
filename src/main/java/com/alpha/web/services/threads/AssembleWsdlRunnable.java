package com.alpha.web.threads;

import com.alpha.core.entity.ServicesInfo;
import com.alpha.core.executor.WsdlAssembleExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * Created by jzhou237 on 2016-09-30.
 */
@Slf4j
public class AssembleWsdlRunnable implements Runnable {

    private String name;

    private WsdlAssembleExecutor wsdlAssembleExecutor;

    private ServicesInfo servicesInfo;

    public AssembleWsdlRunnable(WsdlAssembleExecutor wsdlAssembleExecutor, ServicesInfo servicesInfo) {
        this.name = UUID.randomUUID().toString();
        this.wsdlAssembleExecutor = wsdlAssembleExecutor;
    }

    @Override
    public void run() {
        log.info("Thread [" + this.name + "] start.");
        this.wsdlAssembleExecutor.execute(servicesInfo);
        log.info("Thread [" + this.name + "] end.");
    }

    public String getName() {
        return name;
    }
}
