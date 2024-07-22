package com.bktech.user.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.bktech.user.constants.Constants;
import com.bktech.user.ctx.ExecutionContext;
import com.bktech.user.ctx.UserContext;

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
