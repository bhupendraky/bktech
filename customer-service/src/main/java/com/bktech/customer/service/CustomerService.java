package com.bktech.customer.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bktech.customer.entity.Customer;
import com.bktech.customer.execp.ExceptionCode;
import com.bktech.customer.repository.CustomerRepository;
import com.bktech.infra.constants.Globals;
import com.bktech.infra.execp.AppException;
import com.bktech.user.proxy.UserServiceProxy;
import com.bktech.user.vo.UserVO;

@Service
public class CustomerService {

	private final static Log log = LogFactory.getLog(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Autowired
	private UserServiceProxy userServiceProxy;

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public Customer getCustomer(Long customerId) {
		return customerRepo.findByCustomerId(customerId)
				.orElseThrow(() -> new AppException(ExceptionCode.CUSSVC_0005));
	}

	public String loadCustomerData() {
		var jobParameters = new JobParametersBuilder()
				.addLong("START_AT", System.currentTimeMillis())
				.toJobParameters();
		customerRepo.deleteAll();
		try {
			jobLauncher.run(job, jobParameters);
		} catch (Exception e) {
			log.error(e);
		}
		return Globals.SUCCESS;
	}

	public UserVO getUserByUserName(String username) {
		return userServiceProxy.getUserByUsername(username);
	}

	public List<UserVO> getAllUser() {
		return userServiceProxy.getAllUser();
	}

}
