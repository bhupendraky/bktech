package com.bktech.user.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bktech.user.domain.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUserName(String userName);

	void deleteByUserName(String userName);

	boolean existsByUserName(String userName);
}
