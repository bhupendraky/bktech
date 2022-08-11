package com.tech.hub.url.errors;

import com.tech.hub.common.errors.IErrorCode;

public enum ShortenUrlErrorCode implements IErrorCode {

	TS_03_0001("URL not found"),
	TS_03_0002("Long URL not valid");

	private String defaultMessage;

	ShortenUrlErrorCode(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	@Override
	public String defaultMessage() {
		return this.defaultMessage;
	}

	@Override
	public String errorCode() {
		return name();
	}

}
