package com.bktech.fin.errors;

public enum ExceptionCode implements IExceptionCode {
	FINSVC_0001("Unexpected service exception"),
	FINSVC_0002("Proxy call failed"),
	FINSVC_0003("Unhandled hystrix exception"),
	FINSVC_0004("Method argument not valid"),
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
