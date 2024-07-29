package com.bktech.user.vo;

import com.bktech.app.entity.AuditableEntity;

public class TokenVO extends AuditableEntity<String> {

	private static final long serialVersionUID = -5312370255319044385L;

	private Long id;
	private String value;
	private boolean valid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
