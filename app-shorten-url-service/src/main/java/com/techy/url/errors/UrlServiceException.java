package com.techy.url.errors;

import com.techy.common.errors.AppBaseException;
import com.techy.common.errors.IErrorCode;

public class UrlServiceException extends AppBaseException {

	private static final long serialVersionUID = 1L;

	public UrlServiceException(IErrorCode errorCode) {
		this(errorCode, null, null);
	}

	public UrlServiceException(IErrorCode errorCode, Throwable cause) {
		this(errorCode, null, cause);
	}

	public UrlServiceException(IErrorCode errorCode, String message) {
		this(errorCode, message, null);
	}

	public UrlServiceException(IErrorCode errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

}
