package com.bktech.customer.execp;

import java.util.Optional;

import com.bktech.customer.Application;

public class AppException extends RuntimeException {

	protected static final long serialVersionUID = 1L;

	private IExceptionCode code;

	/**
	 * @param message
	 */
	public AppException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AppException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 */
	public AppException(String message, Throwable cause) {
		super(message, cause);
	}


	/**
	 * @param key
	 * @param args
	 */
	public AppException(String key, Object... args) {
		this(key, null, args);
	}

	/**
	 * @param message
	 * @param args
	 */
	public AppException(String key, Throwable cause, Object... args) {
		this(format(key, args), cause);
	}

	/**
	 * @param code
	 * @param args
	 */
	public AppException(IExceptionCode code, Object... args) {
		this(code, null, args);
	}

	/**
	 * @param code
	 * @param cause
	 */
	public AppException(IExceptionCode code, Throwable cause, Object... args) {
		this(format(code.value(), args), cause);
		this.code = code;
	}

	private static String format(String key, Object... args) {
		return Optional.ofNullable(Application.getContext().getErrorConfig()
				.getProperty(key, args))
				.orElse(key);
	}

	/**
	 * An exception code which uniquely identify a particular exception.
	 *
	 * @return the exception code.
	 */
	public IExceptionCode exceptionCode() {
		return code;
	}

}