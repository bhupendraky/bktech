package com.bktech.user.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;
}
