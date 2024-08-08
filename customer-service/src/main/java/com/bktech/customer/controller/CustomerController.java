package com.bktech.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bktech.customer.entity.Customer;
import com.bktech.customer.service.CustomerService;
import com.bktech.infra.config.Traceable;
import com.bktech.user.vo.UserVO;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Traceable
	@GetMapping("/get-all-customers")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@Traceable
	@GetMapping("/get-customer/{customerId}")
	public Customer getCustomer(@PathVariable Long customerId) {
		return customerService.getCustomer(customerId);
	}

	@Traceable
	@PostMapping("/load-customers")
	public String loadCustomerData() {
		return customerService.loadCustomerData();
	}

	@Traceable
	@GetMapping("/get-user/{username}")
	public UserVO getUserByUserName(@PathVariable String username) {
		return customerService.getUserByUserName(username);
	}

	@Traceable
	@GetMapping("/get-all-users")
	public List<UserVO> getAllUser() {
		return customerService.getAllUser();
	}
}
