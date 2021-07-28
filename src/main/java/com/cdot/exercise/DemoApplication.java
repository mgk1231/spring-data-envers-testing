package com.cdot.exercise;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class DemoApplication {

	@Autowired
	private BookRepository bookRepository;

	@PostMapping("/saveBook")
	public Book saveBook(@RequestBody BookDTO bookdto) {

		List<Author> authors = new ArrayList<>();

		for (AuthorDTO authorDTO : bookdto.getAuthors()) {
			Author author = Author.builder().name(authorDTO.getName()).build();
			authors.add(author);
		}

		Book book = Book.builder().name(bookdto.getName()).pages(bookdto.getPages()).authors(authors).build();

		return bookRepository.save(book);

	}

	@PutMapping("/updateBook/{id}/{pages}")
	public String updateBook(@PathVariable int id, @PathVariable int pages) {

		System.out.println("lsdflsdfkl");

		Book book = bookRepository.findById(id).get();
		book.setPages(pages);
		bookRepository.save(book);

		return "book updated";
	}

	@PutMapping("/updateBookAuthors/{id}")
	public String updateBook(@PathVariable int id, @RequestBody List<AuthorDTO> authorDTOs) {

		System.out.println("lsdflsdfkl");

		List<Author> authors = new ArrayList<>();

		for (AuthorDTO authorDTO : authorDTOs) {
			Author author = Author.builder().name(authorDTO.getName()).build();
			authors.add(author);
		}

		Book book = bookRepository.findById(id).get();
		book.setAuthors(authors);
		bookRepository.save(book);

		return "authors updated";
	}
	
	@PutMapping("/updateBookAuthorTemp/{id}")
	public String updateBook(@PathVariable int id, @RequestBody AuthorDTO authorDTO) {
		
		System.out.println("lsdflsdfkl");
		
		
			Author author = Author.builder().name(authorDTO.getName()).build();
		
		Book book = bookRepository.findById(id).get();
		book.getAuthors().add(author);
		bookRepository.save(book);
		
		return "authors updated";
	}

	@DeleteMapping("/removeBook/{id}")
	public ResponseEntity<?> removeBook(@PathVariable int id) {
		System.out.println("lsdflsdfkl");
		bookRepository.deleteById(id);
		return new ResponseEntity<>("removed successfully", HttpStatus.OK);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
