package com.techy.common.errors;

public class AppBaseException extends RuntimeException {

	protected static final long serialVersionUID = 1L;

	private final IErrorCode errorCode;

	/**
	 * @param errorCode
	 */
	public AppBaseException(IErrorCode errorCode) {
		this(errorCode, resolveMessage(errorCode));
	}

	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public AppBaseException(IErrorCode errorCode, String errorMessage) {
		this(errorCode, errorMessage, null);
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public AppBaseException(IErrorCode errorCode, Throwable cause) {
		this(errorCode, resolveMessage(errorCode), cause);
	}

	/**
	 * @param errorCode
	 * @return
	 */
	private static String resolveMessage(IErrorCode errorCode) {
		return errorCode != null ? errorCode.defaultMessage() : null;
	}

	/**
	 * @param errorCode
	 * @param errorMessage
	 * @param cause
	 */
	public AppBaseException(IErrorCode errorCode, String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorCode = errorCode;
	}

	/**
	 * An error code which uniquely identify a particular exception.
	 *
	 * @return the error code.
	 */
	public IErrorCode errorCode() {
		return errorCode;
	}

	/**
	 * The error message typically set while throwing the exception.
	 *
	 * @return the error message.
	 */
	public String errorMessage() {
		return getMessage();
	}
}