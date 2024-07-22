package com.bktech.infra.execp;

public enum ExceptionCode implements IExceptionCode {
	INFRA_0001("Unexpected service exception"),
	INFRA_0002("Proxy call failed"),
	INFRA_0003("Unhandled hystrix exception"),
	INFRA_0004("Method argument not valid");

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
