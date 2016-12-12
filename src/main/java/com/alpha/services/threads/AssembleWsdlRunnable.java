package com.alpha.services.threads;

import com.alpha.services.entities.ServicesInfo;
import com.alpha.services.repository.ServicesInfoRepository;
import com.alpha.wsdl2java.Wsdl2JavaGenerator;
import com.alpha.wsdl2java.WsdlClassCompiler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by jzhou237 on 2016-09-30.
 */
@Slf4j
public class AssembleWsdlRunnable implements Runnable {

    private String name;

    private ServicesInfo servicesInfo;

    private WsdlClassCompiler wsdlClassCompiler;

    private Wsdl2JavaGenerator wsdl2JavaGenerator;

    private ServicesInfoRepository servicesInfoRepository;

    public AssembleWsdlRunnable(ServicesInfo servicesInfo, WsdlClassCompiler wsdlClassCompiler, Wsdl2JavaGenerator wsdl2JavaGenerator, ServicesInfoRepository servicesInfoRepository) {
        this.name = UUID.randomUUID().toString();
        this.servicesInfo = servicesInfo;
        this.wsdl2JavaGenerator = wsdl2JavaGenerator;
        this.wsdlClassCompiler = wsdlClassCompiler;
        this.servicesInfoRepository = servicesInfoRepository;
    }

    @Override
    public void run() {
        log.info("Thread [" + this.name + "] start.");
        try {
            this.wsdl2JavaGenerator.execute(servicesInfo.getWsdl());
            this.servicesInfo.setWsdl2java(true);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        this.wsdlClassCompiler.compile();
        this.servicesInfoRepository.save(this.servicesInfo);
        log.info("Thread [" + this.name + "] end.");
    }

    public String getName() {
        return name;
    }
}
