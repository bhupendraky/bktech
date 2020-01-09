package com.techy.fin.errors;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FinancialServiceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = FinancialServiceException.class)
	public ResponseEntity<Object> handleUserServiceException(FinancialServiceException ex) {
		return createError(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<Object> createError(FinancialServiceException ex, HttpStatus status) {
		ExceptionResponse expResponse = new ExceptionResponse();
		expResponse.setTimestamp(new Timestamp(new Date().getTime()));
		expResponse.setErrorCode(ex.errorCode());
		expResponse.setMessage(ex.getMessage());
		expResponse.setStatus(status.value());
		return ResponseEntity.status(status).body(expResponse);
	}
}
