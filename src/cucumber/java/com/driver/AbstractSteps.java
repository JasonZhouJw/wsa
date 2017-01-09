package com.driver;

import com.alpha.WsaApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by jzhou237 on 2017-01-09.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WsaApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
public class AbstractSteps {

    protected UiDriver uiDriver = new UiDriverWithHostName();

}
