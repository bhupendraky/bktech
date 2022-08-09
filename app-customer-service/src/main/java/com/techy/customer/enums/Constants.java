package com.techy.customer.enums;

public enum Constants {

	SUCCESS("SUCCESS");

	private String value;

	Constants(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
