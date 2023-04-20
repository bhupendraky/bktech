package com.bktech.user.ctx;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bktech.user.Constants;

@Component
@Order(1)
public class UserContextFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			String userId = null;
			String authUserId = SecurityContextHolder.getContext().getAuthentication().getName();
			if(!Constants.ANONYMOUS_USER.equals(authUserId)) {
				userId = authUserId;
			} else {
				userId = ((HttpServletRequest) request)
						.getHeader(Constants.HTTP_HEADER_USER_ID);
			}
			ExecutionContext.getUserContext().set(new UserContext(userId));
			chain.doFilter(request, response);
		} finally {
			ExecutionContext.removeUserContext();
		}
	}
}
