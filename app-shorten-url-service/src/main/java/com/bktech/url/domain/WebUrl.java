package com.bktech.url.domain;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "WEB_URL")
@EntityListeners(AuditingEntityListener.class)
public class WebUrl extends AuditableEntity<String> {

	private static final long serialVersionUID = 4279835589444017331L;

	@Id
	@GeneratedValue
	private Long id;
	private String hashCode;
	private String url;

	public WebUrl() {
		super();
	}

	public WebUrl(String hashCode, String url) {
		super();
		this.hashCode = hashCode;
		this.url = url;
	}

}
