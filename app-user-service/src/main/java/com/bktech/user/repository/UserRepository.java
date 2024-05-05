package com.bktech.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bktech.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String userName);

	void deleteByUsername(String userName);

	boolean existsByUsername(String userName);
}
