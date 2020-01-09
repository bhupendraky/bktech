package com.techy.user.domain;

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
	@Column(name = "createdby")
	protected U createdBy;

	@CreatedDate
	@Column(name = "createdon")
	protected Date createdOn;

	@LastModifiedBy
	@Column(name = "updatedby")
	protected U updatedBy;

	@LastModifiedDate
	@Column(name = "updatedon")
	protected Date updatedOn;
}
