package com.techy.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {

	@Size(min = 5, message = "User name too short")
	@Size(max = 25, message = "User name too long")
	@NotEmpty
	private String userName;
	@NotEmpty
	@Size(min = 5, message = "Password too short")
	@Size(max = 25, message = "Password too long")
	private String password;
	@Email(regexp = "^([A-Za-z]|[0-9]|(\\.))+@{1}([a-zA-Z])+(\\.){1}[a-zA-Z]+")
	@NotEmpty
	private String email;
	private Boolean enabled = false;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
