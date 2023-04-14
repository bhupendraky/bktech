package com.bktech.user.errors;

public class UserServiceException extends AppBaseException {

	private static final long serialVersionUID = 1L;

	public UserServiceException(IErrorCode errorCode) {
		this(errorCode, null, null);
	}

	public UserServiceException(IErrorCode errorCode, Throwable cause) {
		this(errorCode, null, cause);
	}

	public UserServiceException(IErrorCode errorCode, String message) {
		this(errorCode, message, null);
	}

	public UserServiceException(IErrorCode errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

}
