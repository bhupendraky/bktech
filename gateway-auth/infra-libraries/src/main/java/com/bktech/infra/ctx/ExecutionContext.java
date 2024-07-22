package com.bktech.infra.ctx;

public class ExecutionContext {

	private static final ThreadLocal<AuditContext> auditContext = new ThreadLocal<>();

	public static ThreadLocal<AuditContext> getAuditContext() {
		return auditContext;
	}

	public static void removeAuditContext() {
		auditContext.remove();
	}
}
