package com.bktech.url.ctx;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import com.bktech.url.Constants;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String userId = ExecutionContext.getUserContext().get().getUserId();
		Optional<String> userOp = Optional.ofNullable(userId);
		return Optional.of(userOp.orElse(Constants.APP_DOMAIN));
	}

}
