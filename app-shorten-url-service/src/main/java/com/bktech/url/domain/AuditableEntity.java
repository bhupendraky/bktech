package com.bktech.url.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity<U> implements Serializable {

	private static final long serialVersionUID = 1L;

	@CreatedBy
	protected U createdBy;

	@CreatedDate
	protected Date createdOn;

	@LastModifiedBy
	protected U updatedBy;

	@LastModifiedDate
	protected Date updatedOn;

	public <T> void setAuditFields(AuditableEntity<U> entity) {
		this.setCreatedBy(entity.getCreatedBy());
		this.setCreatedOn(entity.getCreatedOn());
		this.setUpdatedBy(entity.getUpdatedBy());
		this.setUpdatedOn(entity.getUpdatedOn());
	}

}
