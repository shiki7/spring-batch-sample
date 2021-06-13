package com.example.springbatchsample.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job firstJob(Step firstStep) {
        return jobBuilderFactory.get("firstJob") //Job名を指定
                .flow(firstStep) //実行するStepを指定
                .end()
                .build();
    }

    @Bean
    public Job secondJob(Step secondStep) {
        return jobBuilderFactory.get("secondJob") //Job名を指定
                .flow(secondStep) //実行するStepを指定
                .end()
                .build();
    }

    @Bean
    public Job combinationJob(Step firstStep, Step secondStep, Step thirdStep) {
        return jobBuilderFactory.get("combinationJob") //Job名を指定
                .start(firstStep) //実行するStepを指定
                .next(secondStep)
//                .from(secondStep).on("*").fail()
                .next(thirdStep)
                .build();
    }

    @Bean
    public Step firstStep(Tasklet firstTasklet) {
        return stepBuilderFactory.get("firstStep") //Step名を指定
                .tasklet(firstTasklet) //実行するTaskletを指定
                .build();
    }

    @Bean
    public Step secondStep(Tasklet secondTasklet) {
        return stepBuilderFactory.get("secondStep") //Step名を指定
                .tasklet(secondTasklet) //実行するTaskletを指定
                .build();
    }


    @Bean
    public Step thirdStep(Tasklet thirdTasklet) {
        return stepBuilderFactory.get("thirdStep") //Step名を指定
                .tasklet(thirdTasklet) //実行するTaskletを指定
                .build();
    }
}
