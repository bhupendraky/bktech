package com.tech.hub.user.dto;

import com.tech.hub.common.domain.AuditableDTO;

public class AuthorityDTO extends AuditableDTO<String> {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String authority;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
