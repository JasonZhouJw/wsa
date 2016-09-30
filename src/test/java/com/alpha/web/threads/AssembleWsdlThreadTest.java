package com.alpha.web.threads;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by jzhou237 on 2016-09-30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AssembleWsdlThreadTest {

    @Resource
    private Runnable runnable;

    @Test
    public void run() throws Exception {

    }

}