package com.bky.user.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {


	@Pointcut("execution(* com.bky.user.data.PersonJdbcDao.insert(..))"
			+ " || "
			+ "execution(* com.bky.user.data.PersonJdbcDao.update(..))"
			+ " || "
			+ "execution(* com.bky.user.data.PersonJdbcDao.insert(..))"
			+ " || "
			+ "execution(* com.bky.user.data.PersonJdbcDao.update(..))"
			+ " || "
			+ "execution(* org.springframework.data.repository.CrudRepository.save(..))"
			+ " || "
			+ "execution(* com.bky.user.data.PersonEntityManager.save(..))"
			)
	public void beforeSave() {}

	@Pointcut("@annotation(com.bky.user.annotation.Traceble)")
	public Object traceExecution() throws Throwable {
		return null;
	}
}
