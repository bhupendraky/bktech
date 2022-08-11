package com.tech.hub.url.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tech.hub.common.errors.CommonExceptionHandler;

@ControllerAdvice
public class UrlServiceExceptionHandler extends CommonExceptionHandler {

	@ExceptionHandler(value = ShortenUrlServiceException.class)
	public ResponseEntity<Object> handleUserServiceException(ShortenUrlServiceException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(createBody(ex, request));
	}
}
