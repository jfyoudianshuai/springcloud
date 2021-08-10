package com.jf.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EruekaMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(EruekaMain7002.class,args);
    }
}
