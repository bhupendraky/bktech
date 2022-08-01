package com.techy.user.errors;

import com.techy.common.errors.IErrorCode;

public enum UserErrorCode implements IErrorCode {

	TS_01_0001("User not found");

	private String defaultMessage;

	UserErrorCode(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	@Override
	public String defaultMessage() {
		return this.defaultMessage;
	}

	@Override
	public String errorCode() {
		return name();
	}

}
