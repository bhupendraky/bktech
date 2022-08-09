package com.techy.customer.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techy.customer.data.CustomerRepository;
import com.techy.customer.domain.Customer;

@Component
public class CustomerWriter implements ItemWriter<Customer> {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void write(List<? extends Customer> list) throws Exception {
		System.out.println("Thread Name : " + Thread.currentThread().getName());
		customerRepository.saveAll(list);
	}
}
