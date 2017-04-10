package com.alpha.services.domain;

import com.alpha.services.entities.ServicesInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jzhou237 on 2017-03-29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServicesInfoImplTest {

    @Autowired
    private IServicesInfo servicesInfo;

    @Test
    public void findAllActive() throws Exception {
        List<ServicesInfo> servicesInfoList = servicesInfo.findAllActive();
        assertEquals(2, servicesInfoList.size());
        assertEquals(2, servicesInfoList.get(0).getMethodInfoList().size());
        assertNotNull(servicesInfoList.get(0).getMethodInfoList().get(0).getMethod());
    }

}