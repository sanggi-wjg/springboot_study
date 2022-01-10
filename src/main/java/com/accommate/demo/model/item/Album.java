package com.accommate.demo.model.item;

import com.accommate.demo.model.item.Item;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Album")
public class Album extends Item {

    private String artist;
    private String etc;
}