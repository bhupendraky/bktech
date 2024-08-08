package com.bktech.infra.ctx;

public class ExecutionData {

	private final String userId;
	private final String authHeader;

	public ExecutionData() {
		this(null);
	}

	public ExecutionData(String userId) {
		this(userId, null);
	}

	public ExecutionData(String userId, String authHeader) {
		this.userId = userId;
		this.authHeader = authHeader;
	}

	public String getUserId() {
		return userId;
	}

	public String getAuthHeader() {
		return authHeader;
	}

}
