package com.accommate.demo.service;

import com.accommate.demo.model.*;
import com.accommate.demo.model.item.Item;
import com.accommate.demo.repository.ItemRepository;
import com.accommate.demo.repository.MemberRepository;
import com.accommate.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    /*
     * 주문
     * */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // entity 조회
        Member member = memberRepository.findById(memberId);
        Item item = itemRepository.findById(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setDeliveryStatus(DeliveryStatus.READY);

        // 상품 생성
        OrderItem orderItem = OrderItem.create(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.create(member, delivery, orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    /*
     * 주문 취소
     * */
    public void cancel(Long orderId) {
        Order order = orderRepository.findById(orderId);
        order.cancel();
    }

    /*
    * 주문 검색
    * */
//    public List<Order> search(OrderSearch orderSearch){
//
//    }

}
