package com.tech.hub.fin.errors;

import com.tech.hub.common.errors.AppBaseException;
import com.tech.hub.common.errors.IErrorCode;

public class FinancialServiceException extends AppBaseException {

	private static final long serialVersionUID = 1L;

	public FinancialServiceException(IErrorCode errorCode) {
		this(errorCode, null, null);
	}

	public FinancialServiceException(IErrorCode errorCode, Throwable cause) {
		this(errorCode, null, cause);
	}

	public FinancialServiceException(IErrorCode errorCode, String message) {
		this(errorCode, message, null);
	}

	public FinancialServiceException(IErrorCode errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);

	}
}