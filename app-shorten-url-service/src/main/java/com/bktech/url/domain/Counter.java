package com.bktech.url.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
