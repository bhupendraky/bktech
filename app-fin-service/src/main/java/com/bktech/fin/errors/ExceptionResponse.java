package com.bktech.fin.errors;

import java.sql.Timestamp;

public class ExceptionResponse {

	private Integer status;
	private Timestamp timestamp;
	private String message;
	private String details;
	private IErrorCode errorCode;

	private ExceptionResponse(ExceptionResponseBuilder builder) {
		this.status = builder.status;
		this.timestamp = builder.timestamp;
		this.message = builder.message;
		this.details = builder.details;
		this.errorCode = builder.errorCode;
	}

	public static ExceptionResponseBuilder builder() {
		return new ExceptionResponseBuilder();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public IErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(IErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public static class ExceptionResponseBuilder {
		
		private Integer status;
		private Timestamp timestamp;
		private String message;
		private String details;
		private IErrorCode errorCode;

		public ExceptionResponseBuilder status(Integer status) {
			this.status = status;
			return this;
		}

		public ExceptionResponseBuilder timestamp(Timestamp timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public ExceptionResponseBuilder message(String message) {
			this.message = message;
			return this;
		}

		public ExceptionResponseBuilder details(String details) {
			this.details = details;
			return this;
		}

		public ExceptionResponseBuilder errorCode(IErrorCode errorCode) {
			this.errorCode = errorCode;
			return this;
		}

		public ExceptionResponse build() {
			return new ExceptionResponse(this);
		}
	}
}
