package com.bktech.url.execp;

import com.bktech.infra.execp.IExceptionCode;

public enum ExceptionCode implements IExceptionCode {
	URLSVC_0005("app.url.error.short.not.valid"),
	URLSVC_0006("app.url.error.long.not.valid");

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
