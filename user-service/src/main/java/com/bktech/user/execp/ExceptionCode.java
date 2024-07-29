package com.bktech.user.execp;

import com.bktech.infra.execp.IExceptionCode;

public enum ExceptionCode implements IExceptionCode {
	USRSVC_0005("app.user.error.not.found"),
	USRSVC_0006("app.user.error.role.exist"),
	USRSVC_0007("app.user.error.exist"),
	USRSVC_0008("app.user.error.invalid.role"),
	USRSVC_0009("app.user.error.logged.out");

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
