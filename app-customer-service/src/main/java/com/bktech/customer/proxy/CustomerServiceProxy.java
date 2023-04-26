package com.bktech.customer.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.bktech.customer.domain.Customer;

@FeignClient(name = "app-customer-service-proxy", url = "${infra.gateway.url}")
public interface CustomerServiceProxy {

	@GetMapping(value = "/app-customer-service/customer/get/{customerId}")
	Customer getCustomer(Long customerId);

}
