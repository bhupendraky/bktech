package com.bktech.user.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.bktech.user.vo.UserVO;

@FeignClient(name = "user-service-proxy", url = "${infra.gateway.url}")
public interface UserServiceProxy {

	@GetMapping(value = "/user-service/api/user/get/{username}")
	UserVO getUserByUsername();

}
