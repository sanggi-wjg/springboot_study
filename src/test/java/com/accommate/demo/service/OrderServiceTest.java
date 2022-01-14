package com.accommate.demo.service;

import com.accommate.demo.exception.NotEnoughStockQuantityException;
import com.accommate.demo.model.Address;
import com.accommate.demo.model.Member;
import com.accommate.demo.model.Order;
import com.accommate.demo.model.OrderStatus;
import com.accommate.demo.model.item.Book;
import com.accommate.demo.model.item.Item;
import com.accommate.demo.repository.ItemRepository;
import com.accommate.demo.repository.MemberRepository;
import com.accommate.demo.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderService orderService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    private Member createMember() {
        Member member = new Member();
        member.setUsername("User 1");
        member.setAddress(new Address("서울", "강남", "123-456"));
        memberRepository.save(member);
        return member;
    }

    private Item createBook(int price, int stockQuantity) {
        Item book = new Book();
        book.setName("책 이름");
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        itemRepository.save(book);
        return book;
    }

    @Test
    public void testCreateOrder() throws Exception {
        // given
        Member member = this.createMember();

        int bookPrice = 10000;
        int bookStockQuantity = 10;
        Item book = createBook(bookPrice, bookStockQuantity);

        // when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order findOrder = orderRepository.findById(orderId);

        // then
        assertEquals(orderId, findOrder.getId());
        assertEquals(OrderStatus.ORDER, findOrder.getOrderStatus(), "주문 상태는 ORDER");
        assertEquals(bookPrice * orderCount, findOrder.getTotalPrice(), "책가격 * 수량 은 TotalPrice 와 같다");
        assertEquals(bookStockQuantity - orderCount, book.getStockQuantity(), "주문 생성 되면 수량만큼 재고에서 빠져야 한다");
    }

    @Test
    public void testOrderCountExceedStockQuantity() throws Exception {
        // given
        Member member = this.createMember();

        int bookPrice = 10000;
        int bookStockQuantity = 10;
        Item book = createBook(bookPrice, bookStockQuantity);

        // when
        int orderCount = 12;

        // then
        assertThrows(NotEnoughStockQuantityException.class, () -> {
            orderService.order(member.getId(), book.getId(), orderCount); // 재고 수량이 부족해서 에러가 나야 한다.
        });
    }

    @Test
    public void testCancelOrder() throws Exception {
        // given
        Member member = this.createMember();

        int bookPrice = 10000;
        int bookStockQuantity = 10;
        Item book = createBook(bookPrice, bookStockQuantity);

        int orderCount = 5;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // when
        orderService.cancel(orderId);

        // then
        Order findOrder = orderRepository.findById(orderId);

        assertEquals(OrderStatus.CANCEL, findOrder.getOrderStatus(), "주문이 취소되면 CANCEL 로 변경 되어야 한다.");
        assertEquals(bookStockQuantity, book.getStockQuantity(), "주문이 취소되면 수량이 원복 되어야 한다.");
    }
}