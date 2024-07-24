package com.bktech.app.filter;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.bktech.infra.constants.Globals;
import com.bktech.infra.ctx.ExecutionData;
import com.bktech.infra.ctx.ExecutionContext;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuditContextFilter extends AppAuthFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String userId = request.getHeader(Globals.REQ_HEADER_USER_ID);
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		ExecutionData auditorCtx = new ExecutionData(userId, authHeader);
		try {
			ExecutionContext.getAuditContext().set(auditorCtx);
			filterChain.doFilter(request, response);
		} finally {
			ExecutionContext.removeAuditContext();
		}
	}
}
