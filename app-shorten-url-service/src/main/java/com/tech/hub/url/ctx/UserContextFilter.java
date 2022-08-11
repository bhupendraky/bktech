package com.tech.hub.url.ctx;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.tech.hub.common.ctx.UserContextBaseFilter;

@Component
@Order(1)
public class UserContextFilter extends UserContextBaseFilter {

}
