package com.bktech.user.vo;

import com.bktech.app.entity.AuditableEntity;

import java.io.Serial;

public class UserVO extends AuditableEntity<String> {

	@Serial
	private static final long serialVersionUID = -7064942908770052990L;

	private String username;
	private String email;
	private Integer age;
	private String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
