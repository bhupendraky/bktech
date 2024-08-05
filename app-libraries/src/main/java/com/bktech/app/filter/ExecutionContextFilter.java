package com.bktech.app.filter;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.bktech.infra.constants.Globals;
import com.bktech.infra.ctx.ExecutionContext;
import com.bktech.infra.ctx.ExecutionData;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExecutionContextFilter extends AppAuthFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var userId = request.getHeader(Globals.REQ_HEADER_USER_ID);
		var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		var auditorCtx = new ExecutionData(userId, authHeader);
		try {
			ExecutionContext.getAuditContext().set(auditorCtx);
			filterChain.doFilter(request, response);
		} finally {
			ExecutionContext.removeAuditContext();
		}
	}
}
