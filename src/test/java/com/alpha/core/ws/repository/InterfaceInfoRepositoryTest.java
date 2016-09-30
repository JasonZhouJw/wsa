package com.alpha.core.ws.repository;

import com.alpha.core.ws.entity.InterfaceInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by jzhou237 on 2016-09-30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InterfaceInfoRepositoryTest {

    @Autowired
    private InterfaceInfoRepository repository;

    @Test
    public void findByWsdl() throws Exception {
        List<InterfaceInfo> actual = this.repository.findByWsdl(10L);
        System.out.println(actual);
    }

}