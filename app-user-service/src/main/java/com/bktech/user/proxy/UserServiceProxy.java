package com.bktech.user.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "app-user-service-proxy", url = "${infra.gateway.url}")
@RibbonClient(name = "app-user-service-proxy")
public interface UserServiceProxy {

	@GetMapping(value = "/app-user-service/user/getUserId")
	String getUserId();

}
