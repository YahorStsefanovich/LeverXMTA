package com.leverx.leverxspringproj.model;

import lombok.Data;

@Data
public class Address {

    private String author_id;
    private String city;
    private String hhnm;
    private String strt;

    public Address(String author_id, String city, String homeNumber, String streetName) {
        this.author_id = author_id;
        this.city = city;
        this.hhnm = homeNumber;
        this.strt = streetName;
    }
}
