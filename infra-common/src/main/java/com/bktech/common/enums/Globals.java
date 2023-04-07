package com.bktech.common.enums;

public enum Globals {

	HTTP_HEADER_USER_ID("userId"),
	SUCCESS("SUCCESS"),
	FAILURE("FAILURE")
	;

	private String value;

	Globals(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
