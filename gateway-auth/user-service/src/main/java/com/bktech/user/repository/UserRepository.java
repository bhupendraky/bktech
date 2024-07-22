package com.bktech.user.repository;

import java.util.Optional;

import com.bktech.app.repository.AppRepository;
import com.bktech.user.entity.UserEntity;

public interface UserRepository extends AppRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String userName);

	void deleteByUsername(String userName);

	boolean existsByUsername(String userName);
}
