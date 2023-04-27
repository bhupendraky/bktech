package com.bktech.url.entity;

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
@Table(name = "WEB_URL")
@EntityListeners(AuditingEntityListener.class)
public class WebUrl extends AuditableEntity<String> {

	private static final long serialVersionUID = 4279835589444017331L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "HASH_CODE", nullable = false)
	private String hashCode;

	@Column(name = "URL", nullable = false)
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
