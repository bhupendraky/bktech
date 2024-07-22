package com.bktech.url.ctx;

import java.util.Optional;

import com.bktech.url.Constants;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class RequestInterceptorImpl implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		Optional.ofNullable(ExecutionContext.getUserContext().get())
		.ifPresent(ctx -> template.header(Constants.REQ_HEADER_USER_ID, ctx.getUserId()));
	}
}
