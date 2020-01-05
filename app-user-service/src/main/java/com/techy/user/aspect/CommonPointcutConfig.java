package com.techy.user.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {


	@Pointcut("execution(* com.techy.user.data.PersonJdbcDao.insert(..))"
			+ " || "
			+ "execution(* com.techy.user.data.PersonJdbcDao.update(..))"
			+ " || "
			+ "execution(* com.techy.user.data.PersonJdbcDao.insert(..))"
			+ " || "
			+ "execution(* com.techy.user.data.PersonJdbcDao.update(..))"
			+ " || "
			+ "execution(* org.springframework.data.repository.CrudRepository.save(..))"
			+ " || "
			+ "execution(* com.techy.user.data.PersonEntityManager.save(..))"
			)
	public void beforeSave() {}

	@Pointcut("@annotation(com.techy.user.annotation.Traceble)")
	public Object traceExecution() throws Throwable {
		return null;
	}
}
