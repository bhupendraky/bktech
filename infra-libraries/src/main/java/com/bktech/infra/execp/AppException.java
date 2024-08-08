package com.bktech.infra.execp;

import com.bktech.infra.InfraApp;

import java.io.Serial;

public class AppException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	private final transient IExceptionCode code;

	public AppException(String message) {
		super(message);
		this.code = null;
	}

	public AppException(Throwable cause) {
		super(cause);
		this.code = null;
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
		this.code = null;
	}

	public AppException(String message, IExceptionCode code, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public AppException(String key, Object... args) {
		this(key, null, args);
	}

	public AppException(String key, Throwable cause, Object... args) {
		this(format(key, args), cause);
	}

	public AppException(IExceptionCode code, Object... args) {
		this(code, null, args);
	}

	public AppException(IExceptionCode code, Throwable cause, Object... args) {
		this(format(code.key(), args), code, cause);
	}

	private static String format(String key, Object... args) {
		return InfraApp.getContext().getErrorConfig()
				.getProperty(key, args);
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