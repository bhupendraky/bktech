package com.bktech.customer.repository;

import java.util.Optional;

import com.bktech.app.repository.AppRepository;
import com.bktech.customer.entity.Customer;

public interface CustomerRepository extends AppRepository<Customer, Long> {

	Optional<Customer> findByCustomerId(Long customerId);

}
