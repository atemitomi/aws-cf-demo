package com.atemi.ecs.app.batch.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Slf4j
@Service
@Scope(value = SCOPE_PROTOTYPE)
public class CargoDeliveryTasklet implements Tasklet {

    public static final String COUNTRY_NAME = "countryName";

    @Value("${cargo.app.out.base.dir}")
    private String outBaseDir;

    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
        String country = (String) jobParameters.get(COUNTRY_NAME);

        File outDir = Paths.get(outBaseDir).toFile();
        outDir.mkdirs();
        File outFile = new File(outDir.getAbsolutePath() + "/" + country + ".txt");

        log.info("Writing data to {}", outFile.getAbsolutePath());
        Thread.sleep(15000);
        try (FileWriter writer = new FileWriter(outFile)) {
            writer.write(LocalDateTime.now().toString());
        }

        return RepeatStatus.FINISHED;
    }
}
