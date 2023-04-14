package com.bktech.customer.ctx;

import java.util.Optional;

import com.bktech.customer.Constants;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class RequestInterceptorImpl implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		Optional.ofNullable(ExecutionContext.getUserContext().get())
		.ifPresent(ctx -> template.header(Constants.HTTP_HEADER_USER_ID, ctx.getUserId()));
	}
}
