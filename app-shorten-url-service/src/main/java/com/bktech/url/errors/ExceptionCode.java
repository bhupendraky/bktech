package com.bktech.url.errors;

public enum ExceptionCode implements IExceptionCode {
	URLSVC_0001("Unexpected service exception"),
	URLSVC_0002("Proxy call failed"),
	URLSVC_0003("Unhandled hystrix exception"),
	URLSVC_0004("Method argument not valid"),
	URLSVC_0005("URL not found"),
	URLSVC_0006("Long URL not valid");

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
