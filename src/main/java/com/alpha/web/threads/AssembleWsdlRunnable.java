package com.alpha.web.threads;

import com.alpha.core.ws.entity.ServicesInfo;
import com.alpha.core.ws.executor.WsdlAssembleExecutor;
import com.alpha.core.ws.utils.ILog;

import java.util.UUID;

/**
 * Created by jzhou237 on 2016-09-30.
 */
public class AssembleWsdlRunnable implements Runnable, ILog {

    private String name;

    private WsdlAssembleExecutor wsdlAssembleExecutor;

    private ServicesInfo servicesInfo;

    public AssembleWsdlRunnable(WsdlAssembleExecutor wsdlAssembleExecutor, ServicesInfo servicesInfo) {
        this.name = UUID.randomUUID().toString();
        this.wsdlAssembleExecutor = wsdlAssembleExecutor;
    }

    @Override
    public void run() {
        LOGGER.info("Thread [" + this.name + "] start.");
        this.wsdlAssembleExecutor.execute(servicesInfo);
        LOGGER.info("Thread [" + this.name + "] end.");
    }

    public String getName() {
        return name;
    }
}
