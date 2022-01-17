package com.accommate.demo.service;

import com.accommate.demo.controller.BookForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookDto {

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    public UpdateBookDto(BookForm form) {
        name = form.getName();
        price = form.getPrice();
        stockQuantity = form.getStockQuantity();
        author = form.getAuthor();
        isbn = form.getIsbn();
    }
}
