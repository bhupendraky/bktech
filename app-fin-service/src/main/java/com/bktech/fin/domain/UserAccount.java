package com.bktech.fin.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "USER_ACCOUNT")
@EntityListeners(AuditingEntityListener.class)
public class UserAccount extends AuditableEntity<String> {

	private static final long serialVersionUID = -3222077379473577760L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@Column(name = "ACCOUNT_BALANCE", nullable = false)
	private Double accountBalance;

	public UserAccount() {
		super();
	}

	public UserAccount(Long id, Long userId, Double accountBalance) {
		super();
		this.id = id;
		this.userId = userId;
		this.accountBalance = accountBalance;
	}

}
