package com.bktech.user.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bktech.user.domain.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String userName);

	void deleteByUsername(String userName);

	boolean existsByUsername(String userName);
}
