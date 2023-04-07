package com.bktech.fin.errors;

import com.bktech.common.errors.IErrorCode;

public enum FinErrorCode implements IErrorCode {

	TS_02_0001("User account not found");

	private String defaultMessage;

	FinErrorCode(String defaultMessage) {
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
