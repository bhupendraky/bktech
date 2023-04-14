package com.bktech.customer.ctx;

public class UserContext {
	private String userId;

	public UserContext() {
		super();
	}

	public UserContext(String userId) {
		super();
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserContext [userId=" + userId + "]";
	}
}
