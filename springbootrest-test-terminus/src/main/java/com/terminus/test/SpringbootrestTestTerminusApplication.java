package com.terminus.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootrestTestTerminusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootrestTestTerminusApplication.class, args);
    }
}
