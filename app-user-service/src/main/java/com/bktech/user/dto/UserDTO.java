package com.bktech.user.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {

	@Size(min = 2, message = "User name too short")
	@Size(max = 25, message = "User name too long")
	@NotEmpty
	private String username;

	@NotEmpty
	@Size(min = 2, message = "Password too short")
	@Size(max = 25, message = "Password too long")
	private String password;

	@Email(regexp = "^([A-Za-z]|[0-9]|(\\.))+@{1}([a-zA-Z])+(\\.){1}[a-zA-Z]+")
	private String email;

	private Integer age;

	private Set<String> roles = new HashSet<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}
