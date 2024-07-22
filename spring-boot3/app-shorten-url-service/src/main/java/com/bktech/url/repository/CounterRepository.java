package com.bktech.url.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bktech.url.entity.Counter;

public interface CounterRepository extends JpaRepository<Counter, Integer> {

	@Modifying
	@Query(value = "update COUNTER set value = LAST_INSERT_ID(value+1); ", nativeQuery = true)
	void updateCounterValue();

	@Modifying
	@Query(value = "update COUNTER set value = 1; ", nativeQuery = true)
	void resetCounterValue();

}
