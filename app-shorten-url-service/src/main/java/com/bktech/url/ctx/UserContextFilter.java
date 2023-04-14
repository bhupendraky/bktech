package com.bktech.url.ctx;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class UserContextFilter extends UserContextBaseFilter {

}
