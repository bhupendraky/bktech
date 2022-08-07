package com.techy.url.errors;

import com.techy.common.errors.AppBaseException;
import com.techy.common.errors.IErrorCode;

public class ShortenUrlServiceException extends AppBaseException {

	private static final long serialVersionUID = 1L;

	public ShortenUrlServiceException(IErrorCode errorCode) {
		this(errorCode, null, null);
	}

	public ShortenUrlServiceException(IErrorCode errorCode, Throwable cause) {
		this(errorCode, null, cause);
	}

	public ShortenUrlServiceException(IErrorCode errorCode, String message) {
		this(errorCode, message, null);
	}

	public ShortenUrlServiceException(IErrorCode errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

}
