package com.bktech.url.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AuditableEntity<U> implements Serializable {

	private static final long serialVersionUID = 1L;

	@CreatedBy
	@Column(name = "CREATED_BY", nullable = false)
	protected U createdBy;

	@CreatedDate
	@Column(name = "CREATED_DATE", nullable = false)
	protected Date createdDate;

	@LastModifiedBy
	@Column(name = "LAST_MODIFIED_BY", nullable = false)
	protected U lastModifiedBy;

	@LastModifiedDate
	@Column(name = "LAST_MODIFIED_DATE", nullable = false)
	protected Date lastModifiedDate;

	@JsonIgnore
	public <T extends AuditableEntity<U>> void setAuditFields(T entity) {
		if (entity.getCreatedBy() != null) {
			this.setCreatedBy(entity.getCreatedBy());
		}
		if (entity.getCreatedDate() != null) {
			this.setCreatedDate(entity.getCreatedDate());
		}
		if (entity.getLastModifiedBy() != null) {
			this.setLastModifiedBy(entity.getLastModifiedBy());
		}
		if (entity.getLastModifiedDate() != null) {
			this.setLastModifiedDate(entity.getLastModifiedDate());
		}
	}

	public U getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(U createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public U getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(U lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
