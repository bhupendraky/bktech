package com.bktech.user.repository;

import java.util.Optional;

import com.bktech.app.repository.AppRepository;
import com.bktech.user.entity.Token;

public interface TokenRepository extends AppRepository<Token, Long> {

	Optional<Token> findByUserUsernameAndValueAndValid(String username, String value, boolean valid);
}
