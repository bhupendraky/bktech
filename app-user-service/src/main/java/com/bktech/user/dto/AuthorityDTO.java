package com.bktech.user.dto;

import com.bktech.common.domain.AuditableDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorityDTO extends AuditableDTO<String> {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String authority;

}
