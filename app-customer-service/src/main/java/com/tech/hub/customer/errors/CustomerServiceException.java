package com.tech.hub.customer.errors;

import com.tech.hub.common.errors.AppBaseException;
import com.tech.hub.common.errors.IErrorCode;

public class CustomerServiceException extends AppBaseException {

	private static final long serialVersionUID = 1L;

	public CustomerServiceException(IErrorCode errorCode) {
		this(errorCode, null, null);
	}

	public CustomerServiceException(IErrorCode errorCode, Throwable cause) {
		this(errorCode, null, cause);
	}

	public CustomerServiceException(IErrorCode errorCode, String message) {
		this(errorCode, message, null);
	}

	public CustomerServiceException(IErrorCode errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);

	}
}
