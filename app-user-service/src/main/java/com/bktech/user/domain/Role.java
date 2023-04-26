package com.bktech.user.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
