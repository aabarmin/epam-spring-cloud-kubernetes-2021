package com.naya.exams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Evgeny Borisov
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MathApp {
    public static void main(String[] args) {
        SpringApplication.run(MathApp.class);
    }
}
