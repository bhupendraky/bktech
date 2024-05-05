package com.bktech.customer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.bktech.customer.entity.Customer;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	public static final Logger logger = LoggerFactory.getLogger(SpringBatchConfig.class);

	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private PlatformTransactionManager batchTransactionManager;

	@Autowired
	private CustomerWriter customerWriter;

	@Autowired
	private CustomerProcessor customerProcessor;

	@Autowired
	private CustomerFIleReader customerFIleReader;

	@Value("${customer.data.save.chunk.size}")
	private Integer chunkSize;

	@Bean
	Job firstJob() {
		return new JobBuilder("CUSTOMER_LOAD_JOB", jobRepository)
				.incrementer(new RunIdIncrementer())
				.start(chunkStep())
				.next(taskletStep())
				.build();
	}

	@Bean
	Step taskletStep() {
		return new StepBuilder("tasklet step", jobRepository)
				.tasklet((stepContribution, chunkContext) -> {
					logger.info("This is first tasklet step");
					logger.info("SEC = {}", chunkContext.getStepContext().getStepExecutionContext());
					return RepeatStatus.FINISHED;
				}, batchTransactionManager)
				.build();
	}

	@Bean
	Step chunkStep() {
		return new StepBuilder("first step", jobRepository)
				.<Customer, Customer>chunk(chunkSize, batchTransactionManager)
				.reader(customerFIleReader)
				.processor(customerProcessor)
				.writer(customerWriter)
				.build();
	}

}