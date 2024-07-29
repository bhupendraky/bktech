package com.bktech.user.vo;

import java.util.HashSet;
import java.util.Set;

import com.bktech.app.entity.AuditableEntity;

public class UserVO extends AuditableEntity<String> {

	private static final long serialVersionUID = -7064942908770052990L;

	private String username;
	private String email;
	private Integer age;
	private transient boolean enabled = true;
	private transient boolean accountNonExpired = true;
	private transient boolean accountNonLocked = true;
	private transient boolean credentialsNonExpired = true;
	private Set<String> roles = new HashSet<>();
	private TokenVO token;

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public TokenVO getToken() {
		return token;
	}

	public void setToken(TokenVO token) {
		this.token = token;
	}

}
