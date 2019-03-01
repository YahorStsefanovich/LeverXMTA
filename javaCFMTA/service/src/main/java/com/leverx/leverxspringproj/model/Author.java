package com.leverx.leverxspringproj.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Author {

    @Id @GeneratedValue
    private String author_id;
    private String name;

    public Author(String author_id, String name) {
        this.author_id = author_id;
        this.name = name;
    }

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
