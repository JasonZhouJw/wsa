package com.alpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = MustacheAutoConfiguration.class)
//@ConfigurationProperties("classpath:sys.properties")
public class WsaApplication {
    public static void main(String[] args) {
        SpringApplication.run(WsaApplication.class, args);
    }
}
