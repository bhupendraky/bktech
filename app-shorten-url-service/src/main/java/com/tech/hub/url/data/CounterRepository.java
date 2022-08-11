package com.tech.hub.url.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tech.hub.url.domain.Counter;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Integer> {
	
	@Query(value = "update COUNTER set value = LAST_INSERT_ID(value+1); ", nativeQuery = true)
	void updateCounterValue();
}
