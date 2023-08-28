package com.bktech.url.execp;

import java.util.Optional;

import com.bktech.url.Application;

public class AppException extends RuntimeException {

	protected static final long serialVersionUID = 1L;

	private final transient IExceptionCode code;

	/**
	 * @param message
	 */
	public AppException(String message) {
		super(message);
		this.code = null;
	}

	/**
	 * @param cause
	 */
	public AppException(Throwable cause) {
		super(cause);
		this.code = null;
	}

	/**
	 * @param message
	 */
	public AppException(String message, Throwable cause) {
		super(message, cause);
		this.code = null;
	}

	/**
	 * @param message
	 */
	public AppException(String message, IExceptionCode code, Throwable cause) {
		super(message, cause);
		this.code = code;
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
		this(format(code.value(), args), code, cause);
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