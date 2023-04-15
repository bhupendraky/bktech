package com.bktech.url.errors;

public enum ExceptionCode implements IExceptionCode {
	URLSVC_0001("app.url.error.unexpected"),
	URLSVC_0002("app.url.error.proxy.call"),
	URLSVC_0003("app.url.error.hystrix"),
	URLSVC_0004("app.url.error.invalid.arg"),
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
