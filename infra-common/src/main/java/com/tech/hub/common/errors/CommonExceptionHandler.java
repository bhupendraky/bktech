package com.tech.hub.common.errors;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.netflix.hystrix.exception.HystrixRuntimeException;

import feign.FeignException;

public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Create a exception response body.
	 *
	 * @param e
	 * @param request
	 * @return
	 */
	protected Object createBody(AppBaseException e, WebRequest request) {
		log.error("{} - {}", e.errorCode(), e.errorMessage(), e);
		return ExceptionResponse.builder()
				.timestamp(new Timestamp(new Date().getTime()))
				.message(e.errorMessage())
				.details(request.getDescription(false))
				.errorCode(e.errorCode())
				.build();
	}

	/**
	 * Handle generic exception which is not handled properly at source code.
	 *
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	protected final ResponseEntity<Object> unhandledExceptionHandler(Exception e, WebRequest request) {
		AppBaseException ex = new AppBaseException(CommonErrorCode.TS_00_0001, e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(createBody(ex, request));
	}

	/**
	 * Handler for exception <? extends {@link AppBaseException}>.
	 *
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(AppBaseException.class)
	protected final ResponseEntity<Object> handleAppBaseException(AppBaseException e, WebRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(createBody(e, request));
	}

	/**
	 * Exception handler for {@link FeignException} wrapping the actual exception <? extends {@link AppBaseException}>.
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(FeignException.class)
	protected final ResponseEntity<Object> handleProxyException(FeignException e, WebRequest request) {
		Optional<HttpStatus> status = Optional.ofNullable(HttpStatus.resolve(e.status()));
		AppBaseException ex = null;
		if(e.getCause() instanceof AppBaseException) {
			ex = (AppBaseException)e.getCause();
		} else {
			ex = new AppBaseException(CommonErrorCode.TS_00_0002, e);
		}
		return ResponseEntity.status(status.orElse(HttpStatus.SERVICE_UNAVAILABLE))
				.body(createBody(ex, request));
	}

	/**
	 * If hystrix is used in the project then {@link HystrixRuntimeException} is thrown at proxy call
	 * wrapping the {@link FeignException}.
	 *
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(HystrixRuntimeException.class)
	protected final ResponseEntity<Object> handleHystrixException(HystrixRuntimeException e, WebRequest request) {
		if(e.getCause() instanceof FeignException) {
			return handleProxyException((FeignException)e.getCause(), request);
		}
		AppBaseException ex = new AppBaseException(CommonErrorCode.TS_00_0003, e);
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body(createBody(ex, request));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		AppBaseException ex = new AppBaseException(CommonErrorCode.TS_00_0004, e);
		return ResponseEntity.status(status).body(createBody(ex, request));
	}


}
