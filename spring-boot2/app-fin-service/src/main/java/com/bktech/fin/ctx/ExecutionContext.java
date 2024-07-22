package com.bktech.fin.ctx;

public class ExecutionContext {

	private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

	public static ThreadLocal<UserContext> getUserContext() {
		return userContext;
	}

	public static void removeUserContext() {
		userContext.remove();
	}
}
