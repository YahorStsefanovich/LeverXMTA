package com.leverx.leverxspringproj.service;

import com.leverx.leverxspringproj.dao.AuthorDao;
import com.leverx.leverxspringproj.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

	private final AuthorDao authorDao;

	@Autowired
	public AuthorService(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	public List<Author> getAuthorsAll() {
		return authorDao.getAll();
	}
	
	public Author getAuthor(String id) {
		Optional<Author> authorOptional = authorDao.getById(id);
		Author author = null;

		if(authorOptional.isPresent()) {
			author = authorOptional.get();
		}

		return author;
	}
	
	public Author createAuthor(Author author) {
		return authorDao.createEntity(author);
	}
	
	public Author updateAuthor(Author author, String id) {
		return authorDao.updateEntity(author, id);
	}
	
	public String deleteAuthor(String id) {
		return authorDao.deleteEntity(id);
	}
	
}
