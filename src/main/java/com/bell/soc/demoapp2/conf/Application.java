package com.bell.soc.demoapp2.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.ssaad.ami", "com.bell.soc.demoapp2"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
