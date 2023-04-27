package com.bktech.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bktech.user.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

	Optional<Token> findByUserUsernameAndValueAndValid(String username, String value, boolean valid);
}
