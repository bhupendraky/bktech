package com.bktech.fin.domain;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bktech.common.domain.AuditableEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
