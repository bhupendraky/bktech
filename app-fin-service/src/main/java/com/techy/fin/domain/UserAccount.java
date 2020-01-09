package com.techy.fin.domain;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_account")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "findAll", query = "select f from UserAccount f")
public class UserAccount extends AuditableEntity<String> {

	@Id
	@SequenceGenerator(
			name = "seq-id-gen",
			sequenceName = "user_account_id_seq",
			initialValue = 100001,
			allocationSize = 1
			)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-id-gen")
	private Long id;

	private Long userId;

	private Double accountBalance;
}
