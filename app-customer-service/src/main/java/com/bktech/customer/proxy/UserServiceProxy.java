package com.bktech.customer.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bktech.customer.vo.UserVO;

@FeignClient(name = "app-user-service-proxy", url = "${infra.gateway.url}")
public interface UserServiceProxy {

	@GetMapping(value = "/api/user/get/{username}")
	UserVO getUserByUsername(@PathVariable String username);

	@GetMapping(value = "/api/user/get-all")
	List<UserVO> getAllUser();

}
