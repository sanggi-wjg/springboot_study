package com.accommate.demo.model;

import lombok.Getter;

import javax.persistence.*;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) {
    }

    public Address() {

    }
}
