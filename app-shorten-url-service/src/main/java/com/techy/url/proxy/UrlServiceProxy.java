package com.techy.url.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${spring.application.name}", url = "${infra.gateway.url}")
@RibbonClient(name = "${spring.application.name}")
public interface UrlServiceProxy {

	@GetMapping(value = "/app-shorten-url-service/url/{hashCode}")
	String getUrl(String hashCode);

}
