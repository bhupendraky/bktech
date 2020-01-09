package com.techy.user.enums;

public enum Constants {

	HTTP_HEADER_ATTR_USER_ID("userId");

	private String value;

	Constants(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
