package com.bktech.user.dto;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bktech.user.domain.UserEntity;

public class AppUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private boolean enabled;

	public AppUserDetails(UserEntity user) {
		this.username = user.getUserName();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
	}

	public AppUserDetails() {

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO fetch all distinct authorities from data base;
		return Arrays.asList(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
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
