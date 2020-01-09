package com.techy.user.errors;


import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
	private Integer status;
	private Timestamp timestamp;
	private String message;
	private IErrorCode errorCode;
}
