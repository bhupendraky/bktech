package com.tech.hub.fin.domain;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tech.hub.common.domain.AuditableEntity;

@Entity
@Table(name = "USER_ACCOUNT")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "findAll", query = "select f from UserAccount f")
public class UserAccount extends AuditableEntity<String> {

	private static final long serialVersionUID = -3222077379473577760L;

	@Id
	@SequenceGenerator(
			name = "seq-id-gen",
			sequenceName = "USER_ACCOUNT_ID_SEQ",
			initialValue = 100001,
			allocationSize = 1
			)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-id-gen")
	private Long id;

	private Long userId;

	private Double accountBalance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", userId=" + userId + ", accountBalance=" + accountBalance + "]";
	}

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
