package com.atemi.ecs.app.web.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.atemi.ecs.app.batch.tasklet.CargoDeliveryTasklet.COUNTRY_NAME;

@Slf4j
@Service
public class DeliveryService {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job deliveryJob;

    private ExecutorService executorService;

    public DeliveryService() {
        executorService = Executors.newFixedThreadPool(4);
    }

    @SneakyThrows
    public void deliverCargo(String country) {
        JobParametersBuilder paramsBuilder = new JobParametersBuilder();
        paramsBuilder.addString(COUNTRY_NAME, country);
        log.info("Trying to deliver cargo to {}", country);
        executorService.submit(
                () -> jobLauncher.run(deliveryJob, paramsBuilder.toJobParameters()));

    }

    @PreDestroy
    public void destroy() {
        executorService.shutdown();
    }
}
