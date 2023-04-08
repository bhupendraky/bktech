package com.bktech.common.ctx;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import com.bktech.common.Globals;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String userId = ExecutionContext.getUserContext().get().getUserId();
		Optional<String> userOp = Optional.ofNullable(userId);
		return Optional.of(userOp.orElse(Globals.ADMIN_USER));
	}

}
