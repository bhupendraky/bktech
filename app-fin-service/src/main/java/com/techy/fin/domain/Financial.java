package com.techy.fin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "financial")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "findAll", query = "select f from Financial f")
public class Financial extends AuditableEntity<String> {

	@Id
	@SequenceGenerator(
			name = "seq-id-gen",
			sequenceName = "LONG_ID_SEQ",
			initialValue = 100001,
			allocationSize = 1
			)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-id-gen")
	private Long id;

	@Column(name = "NAME")
	private String name;

	public Financial(String name) {
		super();
		this.name = name;
	}
}
