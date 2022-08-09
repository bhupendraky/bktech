package com.techy.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techy.customer.data.CustomerRepository;
import com.techy.customer.domain.Customer;
import com.techy.customer.enums.Constants;
import com.techy.customer.errors.CustomerErrorCode;
import com.techy.customer.errors.CustomerServiceException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public Customer getCustomer(Long customerId) {
		return customerRepo.findByCustomerId(customerId)
				.orElseThrow(() -> new CustomerServiceException(CustomerErrorCode.TS_04_0001));
	}

	public String loadCustomerData() {
		
		return Constants.SUCCESS.value();
	}

}
