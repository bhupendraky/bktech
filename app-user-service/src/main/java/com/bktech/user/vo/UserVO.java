package com.bktech.user.vo;

import java.util.HashSet;
import java.util.Set;

import com.bktech.user.entity.AuditableEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO extends AuditableEntity<String> {

	private static final long serialVersionUID = -7064942908770052990L;

	private String username;
	private String email;
	private Integer age;
	private transient boolean enabled = true;
	private transient boolean accountNonExpired = true;
	private transient boolean accountNonLocked = true;
	private transient boolean credentialsNonExpired = true;
	private Set<String> roles = new HashSet<>();
	private TokenVO token;

}
