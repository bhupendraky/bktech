package com.bktech.fin.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service-proxy", url = "${infra.gateway.url}")
@RibbonClient(name = "user-service-proxy")
public interface UserServiceProxy {

	@GetMapping(value = "/user-service/user/getUserId")
	String getUserId();

}
