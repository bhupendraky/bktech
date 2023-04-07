package com.bktech.user.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.bktech.common.domain.AuditableDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends AuditableDTO<String> {

	private static final long serialVersionUID = 1L;

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
	private boolean enabled = false;
	private Set<AuthorityDTO> authorities;

}
