package com.accommate.demo.repository;

import com.accommate.demo.model.Member;
import com.accommate.demo.model.Order;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
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
        return entityManager.createQuery("SELECT o FROM Order o JOIN Member m", Order.class)
                .getResultList();
    }

    public List<Order> search(OrderSearch orderSearch) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> order = criteriaQuery.from(Order.class);
        Join<Order, Member> member = order.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        if (orderSearch.getOrderStatus() != null) {
            Predicate orderStatus = criteriaBuilder.equal(order.get("orderStatus"), orderSearch.getOrderStatus());
            criteria.add(orderStatus);
        }

        if (StringUtils.hasText(orderSearch.getUsername())) {
            Predicate username = criteriaBuilder.like(member.<String>get("username"),
                    "%" + orderSearch.getUsername() + "%");
            criteria.add(username);
        }

        criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
