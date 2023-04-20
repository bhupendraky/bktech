package com.bktech.user.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
