package com.cdot.exercise;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.envers.AuditJoinTable;
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
public class Book {
	@Id
	@GeneratedValue
	private int id;

	private String name;

	private int pages;

	@AuditJoinTable(name="Book_Author_AUDIT",inverseJoinColumns = @JoinColumn(name = "author_id"))
	@JoinColumn(name="idBook")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Author> authors;

}
