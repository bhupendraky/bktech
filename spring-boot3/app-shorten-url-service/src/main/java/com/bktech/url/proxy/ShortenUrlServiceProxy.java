package com.bktech.url.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "app-shorten-url-service-proxy", url = "${infra.gateway.url}")
public interface ShortenUrlServiceProxy {

	@GetMapping(value = "/app-shorten-url-service/url/{hashCode}")
	String getUrl(String hashCode);

}
