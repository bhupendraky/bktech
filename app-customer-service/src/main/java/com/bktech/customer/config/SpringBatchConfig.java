package com.bktech.customer.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.bktech.customer.entity.Customer;

//@Configuration
//@EnableBatchProcessing
public class SpringBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private CustomerWriter customerWriter;

	@Autowired
	private CustomerProcessor customerProcessor;

	@Autowired
	private CustomerFIleReader customerFIleReader;

	@Value("${customer.data.save.chunk.size}")
	private Integer saveChunkSize;

	@Bean
	public Step step() {
		return stepBuilderFactory.get("step")
				.<Customer, Customer>chunk(saveChunkSize)
				.reader(customerFIleReader)
				.processor(customerProcessor)
				.writer(customerWriter)
				.build();
	}

	@Bean
	public Job runJob() {
		return jobBuilderFactory.get("CUSTOMER_LOAD_BATCH")
				.flow(step())
				.end()
				.build();

	}

	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(4);
		taskExecutor.setCorePoolSize(4);
		taskExecutor.setQueueCapacity(4);
		return taskExecutor;
	}

}