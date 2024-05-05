package com.bktech.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bktech.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerId(Long customerId);

}
