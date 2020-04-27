package com.koy.kono_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class KonoAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(KonoAuthApplication.class, args);
    }

}
