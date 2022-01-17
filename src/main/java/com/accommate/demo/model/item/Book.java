package com.accommate.demo.model.item;

import com.accommate.demo.service.UpdateBookDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Book")
@Getter
@Setter
public class Book extends Item {

    private String author;
    private String isbn;


    public void updateBookInfo(UpdateBookDto param) {
        super.setName(param.getName());
        super.setPrice(param.getPrice());
        super.setStockQuantity(param.getStockQuantity());
        author = param.getAuthor();
        isbn = param.getIsbn();
    }
}
