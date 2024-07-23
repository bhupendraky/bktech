package com.bktech.url.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "url-service-proxy", url = "${infra.gateway.url}")
public interface ShortenUrlServiceProxy {

	@GetMapping(value = "/url-service/url/{hashCode}")
	String getUrl(String hashCode);

}
