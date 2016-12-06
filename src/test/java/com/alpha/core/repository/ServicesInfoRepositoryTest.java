package com.alpha.core.repository;

import com.alpha.core.entity.ServicesInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by jzhou237 on 2016-09-29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicesInfoRepositoryTest {

    @Autowired
    private ServicesInfoRepository repository;

    @Test
    public void findActive() throws Exception {
        List<ServicesInfo> actual = repository.findActive();
        System.out.println(actual.size());
    }

}