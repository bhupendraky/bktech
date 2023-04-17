package com.bktech.user.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USERS")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends AuditableEntity<String> {

	private static final long serialVersionUID = 3169342467018768748L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USER_NAME", nullable = false, unique = true)
	private String userName;

	@Column(name = "USER_PWD", nullable = false)
	private String password;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "AGE")
	private Integer age;

	@Column(name = "ENABLED", nullable = false)
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLES",
	joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
	inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
	private Set<Role> roles = new HashSet<>();

}
