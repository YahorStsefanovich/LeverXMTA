package com.leverx.leverxspringproj.model;

public class Book {

    public Book(String book_id, String name, String author_id) {
        this.book_id = book_id;
        this.name = name;
        this.author_id = author_id;
    }

    public Book(){

    }

    private String book_id;
    private String name;
    private String author_id;

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }
}
