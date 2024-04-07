package com.bktech.url.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COUNTER")
public class Counter {

	@Id
	@Column(name = "ID")
	private Integer id = 1;

	@Column(name = "VALUE", nullable = false)
	private Long value = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}
