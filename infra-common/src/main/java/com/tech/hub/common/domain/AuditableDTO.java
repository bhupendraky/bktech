package com.tech.hub.common.domain;

import java.io.Serializable;
import java.util.Date;

public class AuditableDTO<U> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected U createdBy;

	protected Date createdOn;

	protected U updatedBy;

	protected Date updatedOn;

	public U getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(U createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public U getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(U updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
