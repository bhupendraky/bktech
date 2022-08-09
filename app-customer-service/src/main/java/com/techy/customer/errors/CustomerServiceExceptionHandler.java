package com.techy.customer.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.techy.common.errors.CommonExceptionHandler;

@ControllerAdvice
public class CustomerServiceExceptionHandler extends CommonExceptionHandler {

	@ExceptionHandler(value = CustomerServiceException.class)
	public ResponseEntity<Object> handleUserServiceException(CustomerServiceException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(createBody(ex, request));
	}

}
