package com.leverx.leverxspringproj.model;

public class Book {

    public Book(String bookId, String name, String authorId) {
        this.bookId = bookId;
        this.name = name;
        this.authorId = authorId;
    }

    private String bookId;
    private String name;
    private String authorId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAuthorId() {
        return authorId;
    }
}
