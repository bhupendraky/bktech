package com.techy.common.errors;

public enum ErrorCode implements IErrorCode {

	TS_00_0001("Unexpected service exception"),
	TS_00_0002("Proxy call failed"),
	TS_00_0003("Unhandled hystrix exception"),
	TS_00_0004("Method argument not valid");

	private String defaultMessage;

	ErrorCode(String defaultMessage) {
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
