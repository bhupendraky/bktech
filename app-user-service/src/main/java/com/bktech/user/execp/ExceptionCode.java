package com.bktech.user.execp;

public enum ExceptionCode implements IExceptionCode {
	USRSVC_0001("app.user.error.unexpected"),
	USRSVC_0002("app.user.error.proxy.call"),
	USRSVC_0003("app.user.error.hystrix"),
	USRSVC_0004("app.user.error.invalid.arg"),
	USRSVC_0005("app.user.error.not.found"),
	USRSVC_0006("app.user.error.role.exist"),
	USRSVC_0007("app.user.error.exist"),
	USRSVC_0008("app.user.error.invalid.role");

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
