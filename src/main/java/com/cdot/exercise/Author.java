package com.cdot.exercise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Audited
@Builder
public class Author {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	@JoinColumn(name = "idBook", insertable = false, updatable = false)
	@ManyToOne
	private Book book;

}
