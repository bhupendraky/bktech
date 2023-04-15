package com.bktech.url.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

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

	@NotBlank
	@CreatedBy
	@Column(name = "CREATED_BY", nullable = false)
	protected U createdBy;

	@NotBlank
	@CreatedDate
	@Column(name = "CREATED_DATE", nullable = false)
	protected Date createdDate;

	@NotBlank
	@LastModifiedBy
	@Column(name = "LAST_MODIFIED_BY", nullable = false)
	protected U lastModifiedBy;

	@NotBlank
	@LastModifiedDate
	@Column(name = "LAST_MODIFIED_DATE", nullable = false)
	protected Date lastModifiedDate;

	public <T> void setAuditFields(AuditableEntity<U> entity) {
		this.setCreatedBy(entity.getCreatedBy());
		this.setCreatedDate(entity.getCreatedDate());
		this.setLastModifiedBy(entity.getLastModifiedBy());
		this.setLastModifiedDate(entity.getLastModifiedDate());
	}

}
