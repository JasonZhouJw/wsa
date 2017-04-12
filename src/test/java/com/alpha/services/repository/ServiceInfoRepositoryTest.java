package com.alpha.services.repository;

import com.alpha.services.entities.MethodInfo;
import com.alpha.services.entities.ServiceInfo;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jzhou237 on 2017-03-29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServiceInfoRepositoryTest {

    @Autowired
    private ServiceInfoRepository serviceInfoRepository;

    @Test
    public void save() {
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setName("Test");
        serviceInfo.setWsdl("WSDL");
        serviceInfo.setWsdl2java(true);
        serviceInfo.setUpdatedTime(new Date());
        serviceInfo.setInterfaceClass("InterfaceClass");
        List<MethodInfo> methodInfoList = new ArrayList<>();
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setMethod("Add");
        methodInfo.setServiceInfo(serviceInfo);
        methodInfoList.add(methodInfo);
        methodInfo = new MethodInfo();
        methodInfo.setMethod("Update");
        methodInfo.setServiceInfo(serviceInfo);
        methodInfoList.add(methodInfo);
        serviceInfo.setMethodInfoList(methodInfoList);
        ServiceInfo actual = this.serviceInfoRepository.save(serviceInfo);
        assertNotNull(actual);
        assertNotNull(actual.getId());
    }

    @Test
    public void findOne() throws Exception {
        ServiceInfo serviceInfo = this.serviceInfoRepository.findOne(1L);
        Hibernate.initialize(serviceInfo.getMethodInfoList());
        assertNotNull(serviceInfo);
        assertEquals(2, serviceInfo.getMethodInfoList().size());
        assertNotNull(serviceInfo.getMethodInfoList().get(0).getMethod());
    }
}