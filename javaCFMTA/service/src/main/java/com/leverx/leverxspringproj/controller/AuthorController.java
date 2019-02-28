package com.leverx.leverxspringproj.controller;

import com.leverx.leverxspringproj.model.Author;
import com.leverx.leverxspringproj.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	   
	@GetMapping(value="/Author")
	public List<Author> getAuthorAll() {
		return authorService.getAuthorsAll();
	}
	
	@GetMapping(value="/Author/{id}")
	public Author getAuthor(@PathVariable String id) {
		return authorService.getAuthor(id);
	}    
	
	@PostMapping(value="/Author")
	public Author createAuthor(@RequestBody Author author) {
		return authorService.createAuthor(author);
	}    
	
	@DeleteMapping(value="/Author/{id}")
	public String deleteAuthor(@PathVariable String id) {
		return authorService.deleteAuthor(id);
	}    
	
	@PutMapping(value="/Author")
	public Author updateAuthor(@RequestBody Author author) {
		return authorService.updateAuthor(author);
	}
	
}
