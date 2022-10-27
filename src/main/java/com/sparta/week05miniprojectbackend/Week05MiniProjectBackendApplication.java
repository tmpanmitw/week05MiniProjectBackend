package com.sparta.week05miniprojectbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week05MiniProjectBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week05MiniProjectBackendApplication.class, args);
    }

}
