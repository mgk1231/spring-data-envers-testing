package com.cdot.exercise;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDTO {
	
	private String name;
	private int pages;
	private List<AuthorDTO> authors;

}
