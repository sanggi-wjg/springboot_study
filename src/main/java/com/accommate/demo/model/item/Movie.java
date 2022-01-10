package com.accommate.demo.model.item;

import com.accommate.demo.model.item.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Movie")
public class Movie extends Item {

    private String director;
    private String actor;

}