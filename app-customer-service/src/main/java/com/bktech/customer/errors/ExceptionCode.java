package com.bktech.customer.errors;

public enum ExceptionCode implements IExceptionCode {
	CUSSVC_0001("app.customer.error.unexpected"),
	CUSSVC_0002("app.customer.error.proxy.call"),
	CUSSVC_0003("app.customer.error.hystrix"),
	CUSSVC_0004("app.customer.error.invalid.arg"),
	CUSSVC_0005("app.customer.error.customer.not.found");

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
