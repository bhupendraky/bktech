package com.bktech.fin.errors;

public enum ExceptionCode implements IExceptionCode {
	FINSVC_0001("app.fin.error.unexpected"),
	FINSVC_0002("app.fin.error.proxy.call"),
	FINSVC_0003("app.fin.error.hystrix"),
	FINSVC_0004("app.fin.error.invalid.arg");

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
