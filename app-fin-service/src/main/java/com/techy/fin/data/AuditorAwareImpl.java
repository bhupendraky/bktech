package com.techy.fin.data;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.techy.fin.ctx.ExecutionContext;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String userId = ExecutionContext.getUserContext().get().getUserId();
		Optional<String> userOp = Optional.ofNullable(userId);
		return Optional.of(userOp.orElse("TECHY"));
	}

}
