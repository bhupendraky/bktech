package com.bktech.customer.config;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bktech.customer.data.CustomerRepository;
import com.bktech.customer.domain.Customer;

@Component
public class CustomerWriter implements ItemWriter<Customer> {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void write(Chunk<? extends Customer> chunk) throws Exception {
		System.out.println("Thread Name : " + Thread.currentThread().getName());
		customerRepository.saveAll(chunk);
	}
}
