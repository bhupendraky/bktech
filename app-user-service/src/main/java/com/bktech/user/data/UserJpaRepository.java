package com.bktech.user.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bktech.user.domain.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String userName);

	void deleteByUserName(String userName);
}
