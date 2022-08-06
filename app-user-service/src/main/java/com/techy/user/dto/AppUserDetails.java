package com.techy.user.dto;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.techy.user.domain.User;

public class AppUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private Boolean enabled;

	public AppUserDetails(User user) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.enabled = user.getEnabled();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//TODO fetch all distinct authorities from data base;
		return Arrays.asList(
				new SimpleGrantedAuthority("ADMIN"),
				new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}
