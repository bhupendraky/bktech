package com.bktech.user.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
