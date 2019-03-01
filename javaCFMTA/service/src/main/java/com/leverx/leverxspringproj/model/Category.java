package com.leverx.leverxspringproj.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Category {
    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
