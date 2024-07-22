package com.bktech.customer.execp;

import com.bktech.infra.execp.IExceptionCode;

public enum ExceptionCode implements IExceptionCode {
	CUSSVC_0005("app.customer.error.customer.not.found"),
	CUSSVC_0006("app.customer.error.user.not.found");

	private String key;

	ExceptionCode(String key) {
		this.key = key;
	}

	@Override
	public String key() {
		return this.key;
	}

	@Override
	public String value() {
		return name();
	}

}
