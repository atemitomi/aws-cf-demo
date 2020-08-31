package com.atemi.ecs.app.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CargoJobConfig {
    @Autowired
    protected JobBuilderFactory jobBuilderFactory;
    @Autowired
    protected StepBuilderFactory stepBuilderFactory;
    @Autowired
    protected JobRepository jobRepository;

    @Bean
    protected Step sendCargo(@Autowired Tasklet cargoDeliveryTasklet) {
        return stepBuilderFactory
                .get("send Cargo")
                .tasklet(cargoDeliveryTasklet)
                .build();
    }

    @Bean
    public Job deliveryJob(@Autowired Step sendCargo) {
        return jobBuilderFactory
                .get("CargoDeliveryJob")
                .start(sendCargo)
                .build();
    }
}
