package com.tech.hub.customer.errors;

import com.tech.hub.common.errors.IErrorCode;

public enum CustomerErrorCode implements IErrorCode {

	TS_04_0001("Customer not found");

	private String defaultMessage;

	CustomerErrorCode(String defaultMessage) {
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
