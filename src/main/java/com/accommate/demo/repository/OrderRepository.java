package com.accommate.demo.repository;

import com.accommate.demo.model.Order;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Order order) {
        entityManager.persist(order);
    }

    public Order findById(Long id) {
        return entityManager.find(Order.class, id);
    }

    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o JOIN Member m ", Order.class)
                .getResultList();
    }

    public List<Order> search(OrderSearch orderSearch) {
        return entityManager.createQuery("SELECT o FROM Order o JOIN Member m ", Order.class)
                .getResultList();
    }
}
