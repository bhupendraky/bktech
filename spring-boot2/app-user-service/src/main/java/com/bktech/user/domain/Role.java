package com.bktech.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "name", callSuper = false)
@NoArgsConstructor
@Table(name = "ROLES")
@EntityListeners(AuditingEntityListener.class)
public class Role extends AuditableEntity<String> {

	private static final long serialVersionUID = -3619515111023708280L;

	public Role(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

}
