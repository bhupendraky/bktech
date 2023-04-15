package com.bktech.user.errors;

public enum ExceptionCode implements IExceptionCode {
	USRSVC_0001("Unexpected service exception"),
	USRSVC_0002("Proxy call failed"),
	USRSVC_0003("Unhandled hystrix exception"),
	USRSVC_0004("Method argument not valid"),
	USRSVC_0005("app.user.not.found");

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
