package com.bktech.user.filter;

import java.io.IOException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.bktech.user.constants.Constants;
import com.bktech.user.ctx.ExecutionContext;
import com.bktech.user.ctx.UserContext;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
@ConditionalOnProperty(name = "spring.security.type", havingValue = "NONE")
public class UserContextFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			String userId = ((HttpServletRequest) request)
					.getHeader(Constants.REQ_HEADER_USER_ID);
			ExecutionContext.getUserContext().set(new UserContext(userId));
			chain.doFilter(request, response);
		} finally {
			ExecutionContext.removeUserContext();
		}
	}
}
