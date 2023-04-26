package com.bktech.fin.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "app-user-service-proxy", url = "${infra.gateway.url}")
public interface UserServiceProxy {

	@GetMapping(value = "/app-user-service/user/getUserId")
	String getUserId();

}
