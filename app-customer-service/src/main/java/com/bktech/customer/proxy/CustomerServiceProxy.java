package com.bktech.customer.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.bktech.customer.domain.Customer;

@FeignClient(name = "${spring.application.name}", url = "${infra.gateway.url}")
@RibbonClient(name = "${spring.application.name}")
public interface CustomerServiceProxy {

	@GetMapping(value = "/app-customer-service/customer/get/{customerId}")
	Customer getCustomer(Long customerId);

}
