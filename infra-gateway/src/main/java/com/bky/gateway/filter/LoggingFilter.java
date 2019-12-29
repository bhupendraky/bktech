package com.bky.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class LoggingFilter extends ZuulFilter {

	/**
	 * enabling or disabling based on condition can be controlled by this
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		System.out.println(request);
		return null;
	}

	/**
	 * we can use "pre", "post", "error" etc, check the documentation
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * Use this for ordering in filter chaining
	 */
	@Override
	public int filterOrder() {
		return 1;
	}
}
