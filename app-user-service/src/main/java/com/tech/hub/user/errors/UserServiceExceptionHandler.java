package com.tech.hub.user.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tech.hub.common.errors.CommonExceptionHandler;

@ControllerAdvice
public class UserServiceExceptionHandler extends CommonExceptionHandler {

	@ExceptionHandler(value = UserServiceException.class)
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(createBody(ex, request));
	}
}
