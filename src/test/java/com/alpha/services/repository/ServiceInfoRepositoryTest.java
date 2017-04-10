package com.alpha.services.repository;

import com.alpha.services.entities.MethodInfo;
import com.alpha.services.entities.ServicesInfo;
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
public class ServicesInfoRepositoryTest {

    @Autowired
    private ServicesInfoRepository servicesInfoRepository;

    @Test
    public void save() {
        ServicesInfo servicesInfo = new ServicesInfo();
        servicesInfo.setName("Test");
        servicesInfo.setWsdl("WSDL");
        servicesInfo.setWsdl2java(true);
        servicesInfo.setUpdatedTime(new Date());
        servicesInfo.setInterfaceClass("InterfaceClass");
        List<MethodInfo> methodInfoList = new ArrayList<>();
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setMethod("Add");
        methodInfo.setServicesInfo(servicesInfo);
        methodInfoList.add(methodInfo);
        methodInfo = new MethodInfo();
        methodInfo.setMethod("Update");
        methodInfo.setServicesInfo(servicesInfo);
        methodInfoList.add(methodInfo);
        servicesInfo.setMethodInfoList(methodInfoList);
        ServicesInfo actual = this.servicesInfoRepository.save(servicesInfo);
        assertNotNull(actual);
        assertNotNull(actual.getId());
    }

    @Test
    public void findOne() throws Exception {
        ServicesInfo servicesInfo = this.servicesInfoRepository.findOne(1L);
        Hibernate.initialize(servicesInfo.getMethodInfoList());
        assertNotNull(servicesInfo);
        assertEquals(2, servicesInfo.getMethodInfoList().size());
        assertNotNull(servicesInfo.getMethodInfoList().get(0).getMethod());
    }
}