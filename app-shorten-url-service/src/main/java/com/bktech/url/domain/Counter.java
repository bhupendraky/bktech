package com.bktech.url.domain;

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
	private Integer id = 1;

	private Long value = 1L;

}
