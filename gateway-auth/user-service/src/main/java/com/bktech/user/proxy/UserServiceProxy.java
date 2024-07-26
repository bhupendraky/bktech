package com.bktech.user.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bktech.user.vo.UserVO;


@FeignClient(name = "user-service")
public interface UserServiceProxy {

	@GetMapping(value = "/api/user/get/{username}")
	UserVO getUserByUsername(@PathVariable String username);

	@GetMapping(value = "/api/user/get-all")
	List<UserVO> getAllUser();

}
