package com.bktech.customer.ctx;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bktech.common.ctx.UserContextBaseFilter;

@Component
@Order(1)
public class UserContextFilter extends UserContextBaseFilter {

}
