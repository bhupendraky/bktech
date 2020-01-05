package com.bky.user.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<U> {

	@CreatedBy
	@Column(name = "CREATED_BY")
	protected U createdBy;

	@CreatedDate
	@Column(name = "CREATED_ON")
	protected Date createdOn;

	@LastModifiedBy
	@Column(name = "UPDATED_BY")
	protected U updatedBy;

	@LastModifiedDate
	@Column(name = "UPDATED_ON")
	protected Date updatedOn;
}
