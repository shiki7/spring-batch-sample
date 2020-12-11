package com.example.springbatchsample.config;


import com.example.springbatchsample.tasklet.FirstTasklet;
import com.example.springbatchsample.tasklet.SecondTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final FirstTasklet firstTasklet;

    private final SecondTasklet secondTasklet;

    public BatchConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory,
                       FirstTasklet firstTasklet,
                       SecondTasklet secondTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.firstTasklet = firstTasklet;
        this.secondTasklet = secondTasklet;
    }

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
    public Step firstStep() {
        return stepBuilderFactory.get("firstStep") //Step名を指定
                .tasklet(firstTasklet) //実行するTaskletを指定
                .build();
    }

    @Bean
    public Step secondStep() {
        return stepBuilderFactory.get("secondStep") //Step名を指定
                .tasklet(secondTasklet) //実行するTaskletを指定
                .build();
    }
}
