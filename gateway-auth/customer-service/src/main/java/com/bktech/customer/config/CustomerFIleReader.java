package com.bktech.customer.config;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.bktech.customer.entity.Customer;

@Component
public class CustomerFIleReader extends FlatFileItemReader<Customer> implements InitializingBean{

	@Value("${customer.data.file.path}")
	private String customerDataFilePath;

	@Value("${customer.data.file.header}")
	private String[] customerDataHeaders;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.setResource(new FileSystemResource(customerDataFilePath));
		this.setName("csvReader");
		this.setLinesToSkip(1);
		this.setLineMapper(lineMapper());
		super.afterPropertiesSet();
	}
	
	private LineMapper<Customer> lineMapper() {
		DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(customerDataHeaders);

		BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Customer.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;

	}

}
