package com.example.wj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class WjApplication {

    public static void main(String[] args) {
        SpringApplication.run(WjApplication.class, args);
    }

}
