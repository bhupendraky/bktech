package com.bktech.url.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "url-service")
public interface ShortenUrlServiceProxy {

	@GetMapping(value = "/url/{hashCode}")
	String getUrl(String hashCode);

}
