package com.bktech.user.security;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bktech.app.constants.RoleType;

public class UserDetailsImpl implements UserDetails {

	@Serial
	private static final long serialVersionUID = 7657093843522128049L;

	private String username;

	private String password;

	public UserDetailsImpl(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public UserDetailsImpl() {
	}

	@Override public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(RoleType.ADMIN.name()));
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
