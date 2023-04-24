package com.bktech.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "TOKENS")
@EntityListeners(AuditingEntityListener.class)
public class Token extends AuditableEntity<String> {

	private static final long serialVersionUID = -5312370255319044385L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "VALUE", nullable = false)
	private String value;

	@Column(name = "VALID", nullable = false)
	private boolean valid;

	@OneToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID" , nullable = false)
	private UserEntity user;
}
