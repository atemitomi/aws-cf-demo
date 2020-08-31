package com.atemi.ecs.app;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication(scanBasePackages = "com.atemi.ecs.app")
public class BatchJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchJobApplication.class, args);
    }
}
