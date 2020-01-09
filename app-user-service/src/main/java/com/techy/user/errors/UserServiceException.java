package com.techy.user.errors;

public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private IErrorCode errorCode;

	public UserServiceException(IErrorCode errorCode) {
		this(errorCode, errorCode == null ? null : errorCode.defaultMessage(), null);
	}

	public UserServiceException(IErrorCode errorCode, Throwable cause) {
		this(errorCode, null, cause);
	}

	public UserServiceException(IErrorCode errorCode, String message) {
		this(errorCode, message, null);
	}

	public UserServiceException(IErrorCode errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public IErrorCode errorCode() {
		return this.errorCode;
	}

}
