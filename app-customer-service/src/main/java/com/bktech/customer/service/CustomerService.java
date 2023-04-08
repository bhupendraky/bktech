package com.bktech.customer.service;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bktech.common.Globals;
import com.bktech.customer.data.CustomerRepository;
import com.bktech.customer.domain.Customer;
import com.bktech.customer.errors.CustomerErrorCode;
import com.bktech.customer.errors.CustomerServiceException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public Customer getCustomer(Long customerId) {
		return customerRepo.findByCustomerId(customerId)
				.orElseThrow(() -> new CustomerServiceException(CustomerErrorCode.TS_04_0001));
	}

	public String loadCustomerData() {
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("startAt", System.currentTimeMillis())
				.toJobParameters();
		customerRepo.deleteAll();
		try {
			jobLauncher.run(job, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
		return Globals.SUCCESS;
	}

}
