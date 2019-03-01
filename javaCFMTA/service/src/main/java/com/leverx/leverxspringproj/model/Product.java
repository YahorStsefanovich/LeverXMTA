package com.leverx.leverxspringproj.model;

import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

@Data
public class Product {

    public Product(int id, String name, String description, GregorianCalendar releaseDate, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    private int id;
    private String name;
    private String description;
    private GregorianCalendar releaseDate;
    private BigDecimal price;
    private Category category;
}
