package com.bktech.customer.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bktech.customer.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByCustomerId(Long customerId);

}
