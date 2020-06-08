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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "findAll", query = "select u from User u")
public class User extends AuditableEntity<String> {

	@Id
	@SequenceGenerator(
			name = "seq-id-gen",
			sequenceName = "user_id_seq",
			initialValue = 100001,
			allocationSize = 1
			)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-id-gen")
	private Long id;

	private String name;

	public User(String name) {
		super();
		this.name = name;
	}
}
