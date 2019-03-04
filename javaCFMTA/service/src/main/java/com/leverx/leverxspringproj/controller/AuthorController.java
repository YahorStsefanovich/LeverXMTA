package com.leverx.leverxspringproj.controller;

import com.leverx.leverxspringproj.exception.AuthorNotFoundException;
import com.leverx.leverxspringproj.model.Author;
import com.leverx.leverxspringproj.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@GetMapping(value="/Author")
	public ResponseEntity<?> getAuthorAll() {
		return new ResponseEntity<>(authorService.getAuthorsAll(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping(value="/Author/{id}")
	public ResponseEntity<?> getAuthor(@PathVariable String id) {
		return new ResponseEntity<>(authorService.getAuthor(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping(value="/Author")
	public ResponseEntity<?> createAuthor(@RequestBody Author author) {
		return  ResponseEntity.created(null).body(authorService.createAuthor(author)) ;
	}    
	
	@DeleteMapping(value="/Author/{id}")
	public ResponseEntity<?> deleteAuthor(@PathVariable String id) {
		return new ResponseEntity<>(authorService.deleteAuthor(id), new HttpHeaders(), HttpStatus.NO_CONTENT);
	}    
	
	@PutMapping(value="/Author/{id}")
	public ResponseEntity<?> updateAuthor(@RequestBody Author author, @PathVariable String id) {
		return new ResponseEntity<>(authorService.updateAuthor(author, id), new HttpHeaders(), HttpStatus.OK);
	}
	
}
