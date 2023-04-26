package com.bktech.customer.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	@Column(name = "ID")
	private Long id;

	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "DATE_OF_BIRTH")
	private String dateOfBirth;

}
