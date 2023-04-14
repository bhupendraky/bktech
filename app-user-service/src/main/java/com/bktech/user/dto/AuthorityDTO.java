package com.bktech.user.dto;

import com.bktech.user.domain.AuditableEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorityDTO extends AuditableEntity<String> {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String authority;

}
