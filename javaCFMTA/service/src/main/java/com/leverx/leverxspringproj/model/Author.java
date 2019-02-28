package com.leverx.leverxspringproj.model;

import java.util.List;

public class Author {

    public Author(String author_id, String name) {
        this.author_id = author_id;
        this.name = name;
    }

    private String author_id;
    private String name;

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
