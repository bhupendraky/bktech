package com.bktech.url.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "COUNTER")
public class Counter {

	@Id
	@Column(name = "ID")
	private Integer id = 1;

	@Column(name = "VALUE", nullable = false)
	private Long value = 1L;

}
