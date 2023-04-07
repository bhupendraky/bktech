package com.bktech.user.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bktech.common.domain.AuditableEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "APP_USERS")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "findAllUser", query = "select u from User u")
public class User extends AuditableEntity<String> {

	private static final long serialVersionUID = 3169342467018768748L;

	@Id
	@GeneratedValue
	private Long id;
	private String userName;
	private String password;
	private String email;
	private boolean enabled;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Authority> authorities = new HashSet<>();

}
