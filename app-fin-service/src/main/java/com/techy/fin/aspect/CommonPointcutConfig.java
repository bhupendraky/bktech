package com.techy.fin.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {

	@Pointcut("@annotation(com.techy.fin.annotation.Traceble)")
	public Object traceExecution() throws Throwable {
		return null;
	}
}
