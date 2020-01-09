package com.techy.fin.errors;

public class FinancialServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private IErrorCode errorCode;

	public FinancialServiceException(IErrorCode errorCode) {
		this(errorCode, errorCode == null ? null : errorCode.defaultMessage(), null);
	}

	public FinancialServiceException(IErrorCode errorCode, Throwable cause) {
		this(errorCode, null, cause);
	}

	public FinancialServiceException(IErrorCode errorCode, String message) {
		this(errorCode, message, null);
	}

	public FinancialServiceException(IErrorCode errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public IErrorCode errorCode() {
		return this.errorCode;
	}

}
