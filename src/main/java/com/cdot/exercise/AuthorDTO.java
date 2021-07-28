package com.cdot.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorDTO {

	private int id;

	private String name;

	private BookDTO bookDTO;

}
