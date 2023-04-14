package com.bktech.fin.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class FinancialServiceExceptionHandler extends CommonExceptionHandler {

	@ExceptionHandler(value = FinancialServiceException.class)
	public ResponseEntity<Object> handleUserServiceException(FinancialServiceException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(createBody(ex, request));
	}

}
