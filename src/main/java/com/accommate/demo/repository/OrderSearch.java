package com.accommate.demo.repository;

import com.accommate.demo.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String username;

    private OrderStatus orderStatus;

}
