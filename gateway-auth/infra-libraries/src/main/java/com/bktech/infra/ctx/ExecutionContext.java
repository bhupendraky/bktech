package com.bktech.infra.ctx;

public class ExecutionContext {

	private static final ThreadLocal<ExecutionData> executionData = new ThreadLocal<>();

	public static ThreadLocal<ExecutionData> getAuditContext() {
		return executionData;
	}

	public static void removeAuditContext() {
		executionData.remove();
	}
}
