package com.bktech.customer.execp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.netflix.hystrix.exception.HystrixRuntimeException;

import feign.FeignException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Create a exception response body.
	 *
	 * @param e
	 * @param request
	 * @return
	 */
	protected Object createBody(AppException e, WebRequest request) {
		log.error("{} - {}", e.exceptionCode(), e.getMessage(), e);
		return ExceptionResponse.builder()
				.timestamp(new Timestamp(new Date().getTime()))
				.message(e.getMessage())
				.details(request.getDescription(false))
				.errorCode(e.exceptionCode())
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
		AppException ex = new AppException(ExceptionCode.CUSSVC_0001, e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(createBody(ex, request));
	}

	/**
	 * Handler for exception <? extends {@link AppException}>.
	 *
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(AppException.class)
	protected final ResponseEntity<Object> handleAppBaseException(AppException e, WebRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(createBody(e, request));
	}

	/**
	 * Exception handler for {@link FeignException} wrapping the actual exception <? extends {@link AppException}>.
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(FeignException.class)
	protected final ResponseEntity<Object> handleProxyException(FeignException e, WebRequest request) {
		Optional<HttpStatus> status = Optional.ofNullable(HttpStatus.resolve(e.status()));
		AppException ex = null;
		if (e.getCause() instanceof AppException) {
			ex = (AppException)e.getCause();
		} else {
			ex = new AppException(ExceptionCode.CUSSVC_0002, e);
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
		if (e.getCause() instanceof FeignException) {
			return handleProxyException((FeignException)e.getCause(), request);
		}
		AppException ex = new AppException(ExceptionCode.CUSSVC_0003, e);
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body(createBody(ex, request));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		AppException ex = new AppException(ExceptionCode.CUSSVC_0004, e);
		return ResponseEntity.status(status).body(createBody(ex, request));
	}

}
