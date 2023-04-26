package com.bktech.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bktech.customer.config.Traceble;
import com.bktech.customer.domain.Customer;
import com.bktech.customer.service.CustomerService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Traceble
	@GetMapping("/get-all")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@Traceble
	@GetMapping("/get/{customerId}")
	public Customer getCustomer(@PathVariable Long customerId) {
		return customerService.getCustomer(customerId);
	}

	@Traceble
	@PostMapping("/load")
	public String loadCustomerData() {
		return customerService.loadCustomerData();
	}
}
