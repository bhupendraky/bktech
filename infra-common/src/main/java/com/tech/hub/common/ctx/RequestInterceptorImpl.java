package com.tech.hub.common.ctx;

import java.util.Optional;

import com.tech.hub.common.enums.Globals;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class RequestInterceptorImpl implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		Optional.ofNullable(ExecutionContext.getUserContext().get())
		.ifPresent(ctx -> template.header(Globals.HTTP_HEADER_USER_ID.value(), ctx.getUserId()));
	}
}
