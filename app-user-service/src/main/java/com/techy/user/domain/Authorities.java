package com.techy.user.domain;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.techy.common.domain.AuditableEntity;

@Entity
@Table(name = "AUTHORITIES")
@EntityListeners(AuditingEntityListener.class)
public class Authorities extends AuditableEntity<String> {

	@Id
	private String userName;
	
	private String authority;

	public Authorities() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
