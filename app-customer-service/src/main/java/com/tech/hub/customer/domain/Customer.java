package com.tech.hub.customer.domain;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.tech.hub.common.domain.AuditableEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CUSTOMER")
@EntityListeners(AuditingEntityListener.class)
public class Customer extends AuditableEntity<String> {

	private static final long serialVersionUID = -7761789872746333319L;

	@Id
	@GeneratedValue
	private Long id;
	private Long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String contactNumber;
	private String country;
	private String dateOfBirth;

}
