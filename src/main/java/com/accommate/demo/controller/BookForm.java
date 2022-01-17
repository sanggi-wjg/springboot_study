package com.accommate.demo.controller;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class BookForm {

    private Long id;

    @NotEmpty(message = "상품 이름은 필수 입니다.")
    private String name;

    @NotNull
    @Digits(message = "가격", integer = 10, fraction = 0)
    private int price;

    @NotNull
    @Digits(message = "가격", integer = 10, fraction = 0)
    private int stockQuantity;

    private String author;
    private String isbn;

}
