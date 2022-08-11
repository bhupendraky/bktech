package com.tech.hub.user.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${spring.application.name}", url = "${infra.gateway.url}")
@RibbonClient(name = "${spring.application.name}")
public interface UserServiceProxy {

	@GetMapping(value = "/app-user-service/user/getUserId")
	String getUserId();

}
