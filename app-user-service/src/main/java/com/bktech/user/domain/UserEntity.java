package com.bktech.user.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USERS")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends AuditableEntity<String> implements UserDetails {

	private static final long serialVersionUID = 3169342467018768748L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "USER_NAME", nullable = false, unique = true)
	private String username;

	@Column(name = "USER_PWD", nullable = false)
	private String password;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "AGE")
	private Integer age;

	private transient boolean enabled = true;
	private transient boolean accountNonExpired = true;
	private transient boolean accountNonLocked = true;
	private transient boolean credentialsNonExpired = true;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "USER_ROLES",
	joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
	inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
	private Set<Role> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toSet());
	}

}
