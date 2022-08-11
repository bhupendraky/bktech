package com.tech.hub.user.domain;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tech.hub.common.domain.AuditableEntity;

@Entity
@Table(name = "TECHY_USER")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "findAllUser", query = "select u from User u")
public class User extends AuditableEntity<String> {

	@Id
	private String userName;
	private String password;
	private String email;
	private Boolean enabled;

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
