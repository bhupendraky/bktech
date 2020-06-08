package com.techy.common.ctx;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.techy.common.enums.Globals;

@Component
@Order(1)
public class UserContextBaseFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest servletRequest = (HttpServletRequest) request;
			String userId = servletRequest.getHeader(Globals.HTTP_HEADER_ATTR_USER_ID.value());
			UserContext userContext = new UserContext();
			userContext.setUserId(userId);
			ExecutionContext.getUserContext().set(userContext);
			chain.doFilter(request, response);
		} finally {
			ExecutionContext.removeUserContext();
		}
	}

}
