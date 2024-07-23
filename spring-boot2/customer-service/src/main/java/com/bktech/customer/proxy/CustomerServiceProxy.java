package com.bktech.customer.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.bktech.customer.domain.Customer;

@FeignClient(name = "customer-service-proxy", url = "${infra.gateway.url}")
@RibbonClient(name = "customer-service-proxy")
public interface CustomerServiceProxy {

	@GetMapping(value = "/customer-service/customer/get/{customerId}")
	Customer getCustomer(Long customerId);

}
