package com.bktech.user.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bktech.common.domain.AuditableEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "AUTHORITIES")
@EntityListeners(AuditingEntityListener.class)
public class Authority extends AuditableEntity<String> {

	private static final long serialVersionUID = -3619515111023708280L;

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userName", referencedColumnName = "userName")
	private User user;
	private String authority;

}
