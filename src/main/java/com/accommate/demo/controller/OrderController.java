package com.accommate.demo.controller;

import com.accommate.demo.model.Member;
import com.accommate.demo.model.Order;
import com.accommate.demo.model.OrderStatus;
import com.accommate.demo.model.item.Item;
import com.accommate.demo.repository.OrderSearch;
import com.accommate.demo.service.ItemService;
import com.accommate.demo.service.MemberService;
import com.accommate.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        model.addAttribute("members", memberService.findMembers());
        model.addAttribute("items", itemService.findItems());

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {

        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> findOrders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", findOrders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancel(orderId);
        return "redirect:/orders";
    }

}
