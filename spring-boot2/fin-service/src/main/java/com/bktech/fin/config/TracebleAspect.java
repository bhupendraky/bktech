package com.bktech.fin.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;


@Aspect
@Configuration
public class TracebleAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ObjectMapper mapper = new ObjectMapper();

	@Around("@annotation(com.bktech.fin.config.Traceble)")
	public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
		if (!logger.isInfoEnabled()) {
			return pjp.proceed();
		}
		Class<? extends Object> targetClass = pjp.getTarget().getClass();
		String methodName = pjp.getSignature().getName();
		String arguments = mapper.writeValueAsString(pjp.getArgs());

		logger.info("START OF {}#{}({})", targetClass, methodName, arguments);
		long start = System.currentTimeMillis();
		Object result = pjp.proceed();
		long executionTime = System.currentTimeMillis() - start;
		logger.info("TIME TAKEN BY {}#{}({}) is {} ms", targetClass, methodName, arguments, executionTime);
		if (logger.isDebugEnabled()) {
			logger.debug("RESULT OF {}#{}({}) is {}", targetClass, methodName, arguments,
					mapper.writeValueAsString(result));
		}
		logger.info("END OF {}#{}({})", targetClass, methodName, arguments);
		return result;
	}

}
