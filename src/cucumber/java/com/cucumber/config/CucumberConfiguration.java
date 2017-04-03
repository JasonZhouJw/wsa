package com.cucumber.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by jzhou237 on 2017-02-27.
 */
@Configuration
@ComponentScan(basePackages = "com.cucumber")
@EnableJpaRepositories(basePackages = {"com.cucumber", "com.alpha"})
public class CucumberConfiguration {

}
