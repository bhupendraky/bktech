package com.bktech.url.ctx;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bktech.url.Constants;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
@Order(1)
public class UserContextFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			ExecutionContext.getUserContext()
			.set(new UserContext(((HttpServletRequest) request)
					.getHeader(Constants.REQ_HEADER_USER_ID)));
			chain.doFilter(request, response);
		} finally {
			ExecutionContext.removeUserContext();
		}
	}
}
