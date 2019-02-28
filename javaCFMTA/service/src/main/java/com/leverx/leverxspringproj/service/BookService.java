package com.leverx.leverxspringproj.service;

import com.leverx.leverxspringproj.dao.BookDao;
import com.leverx.leverxspringproj.model.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
	
	private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getBookAll() {
        return bookDao.getAll();
    }

    public Book getBook(String id) {
        Optional<Book> bookOptional = this.bookDao.getById(id);
        Book book = null;

        if (bookOptional.isPresent()) {
            book = bookOptional.get();
        }

        return book;
    }

    public Book createBook(Book book) {
        return bookDao.createEntity(book);
    }

    public Book updateBook(Book book)  {
        return bookDao.updateEntity(book);
    }

    public String deleteBook(String id) {
        return bookDao.deleteEntity(id);
    }

}
