package com.cdot.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
	public Book saveBook(@RequestBody Book book) {

		return bookRepository.save(book);

	}

	@PutMapping("/updateBook/{id}/{pages}")
	public String updateBook(@PathVariable int id, @PathVariable int pages) {

		Book book = bookRepository.findById(id).get();
		book.setPages(pages);
		bookRepository.save(book);

		return "book updated";
	}

	@DeleteMapping("/removeBook/{id}")
	public String removeBook(@PathVariable int id) {
		bookRepository.deleteById(id);
		return "removed successfully";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
