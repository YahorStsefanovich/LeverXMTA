package com.leverx.leverxspringproj.model;

import java.util.List;

public class Author {

    public Author(String authorId, String name, List<Book> bookList) {
        this.authorId = authorId;
        this.name = name;
        this.bookList = bookList;
    }

    private String authorId;
    private String name;
    private List<Book> bookList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
