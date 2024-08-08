package com.bktech.url.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bktech.app.entity.AuditableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;

@Entity
@Table(name = "WEB_URL")
@EntityListeners(AuditingEntityListener.class)
public class WebUrl extends AuditableEntity<String> {

	@Serial
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
