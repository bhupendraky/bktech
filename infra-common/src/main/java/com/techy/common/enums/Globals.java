package com.techy.common.enums;

public enum Globals {

	HTTP_HEADER_ATTR_USER_ID("userId");

	private String value;

	Globals(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
