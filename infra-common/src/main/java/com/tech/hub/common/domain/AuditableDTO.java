package com.tech.hub.common.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditableDTO<U> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected U createdBy;

	protected Date createdOn;

	protected U updatedBy;

	protected Date updatedOn;

}
