package com.tech.hub.fin.enums;

public enum Constants {

	TEST("test");

	private String value;

	Constants(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
