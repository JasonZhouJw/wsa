package com.steps;

import com.alpha.WsaApplication;
import com.cucumber.config.ApplicationConfigurations;
import com.cucumber.config.CucumberConfiguration;
import com.cucumber.driver.UiDriver;
import com.cucumber.pages.LoginPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

/**
 * Created by jzhou237 on 2017-02-27.
 */
@ContextConfiguration(classes = WsaApplication.class, loader = SpringBootContextLoader.class)
@SpringBootTest(classes = {CucumberConfiguration.class}, webEnvironment = DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class Hooks {

    @Autowired
    private UiDriver uiDriver;

    @Autowired
    private ApplicationConfigurations applicationConfigurations;

    @Autowired
    private LoginPage loginPage;

    @Before("@user")
    public void signIn() {
        loginPage.login("admin", "admin");
    }

    @After
    public void closeUiDriver() {
        uiDriver.close();
    }

    @After("@restoreApplicationConfiguration")
    public void restoreApplicationConfiguration() {
        applicationConfigurations.restore();
    }

}
