package com.leverx.leverxspringproj.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Book {

    @Id @GeneratedValue
    private String book_id;
    private String name;
    private String author_id;

    public Book(String book_id, String name, String author_id) {
        this.book_id = book_id;
        this.name = name;
        this.author_id = author_id;
    }

    public Book() {

    }
}
