package com.bktech.customer.errors;

public enum ExceptionCode implements IExceptionCode {
	CUSSVC_0001("Unexpected service exception"),
	CUSSVC_0002("Proxy call failed"),
	CUSSVC_0003("Unhandled hystrix exception"),
	CUSSVC_0004("Method argument not valid"),
	CUSSVC_0005("Customer not found")
	;

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
