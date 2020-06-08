package com.techy.common.errors;


import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionResponse {
	private Integer status;
	private Timestamp timestamp;
	private String message;
	private String details;
	private IErrorCode errorCode;
}
