package com.techy.url.domain;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.techy.common.domain.AuditableEntity;

@Entity
@Table(name = "WEB_URL")
@EntityListeners(AuditingEntityListener.class)
public class WebUrl extends AuditableEntity<String> {

	@Id
	private String hashCode;
	private String url;

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
