package com.leverx.leverxspringproj.model;

import lombok.Data;

@Data
public class Author {

    private String author_id;
    private String name;

    public Author(String author_id, String name) {
        this.author_id = author_id;
        this.name = name;
    }

}
