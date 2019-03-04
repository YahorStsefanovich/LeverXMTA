package com.leverx.leverxspringproj.model;

import lombok.Data;

import javax.persistence.*;

@Data
public class Author {

    @Id
    @Column(name = "\"author_id\"")
    private String author_id;
    @Column(name = "\"name\"")
    private String name;

    public Author(String author_id, String name) {
        this.author_id = author_id;
        this.name = name;
    }

}
