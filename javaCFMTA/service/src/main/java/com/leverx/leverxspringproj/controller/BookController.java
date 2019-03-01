package com.leverx.leverxspringproj.controller;

import com.leverx.leverxspringproj.model.Book;
import com.leverx.leverxspringproj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value="/Book")
    public List<Book> getBookAll() {
        return bookService.getBookAll();
    }

    @GetMapping(value="/Book/{id}")
    public Book getBook(@PathVariable String id) {
        return bookService.getBook(id);
    }

    @PostMapping(value="/Book",
                consumes = {MediaType.APPLICATION_JSON_VALUE},
                produces = {MediaType.APPLICATION_JSON_VALUE})
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping(value="/Book/{id}")
    public String deleteBook(@PathVariable String id) {
        return bookService.deleteBook(id);
    }

    @PutMapping(value="/Book/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable String id) {
        return bookService.updateBook(book, id);
    }

}
