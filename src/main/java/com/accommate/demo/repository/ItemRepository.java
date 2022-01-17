package com.accommate.demo.repository;

import com.accommate.demo.model.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Item item) {
        /*if
        update 시에 merge 를 사용하는 것보다 entity 에서 변경 감지를 사용하는 것이 더 좋음
        (item.getId() == null) {
            entityManager.persist(item);
        } else {
            entityManager.merge(item);
        }*/
        entityManager.persist(item);
    }

    public Item findById(Long id) {
        return entityManager.find(Item.class, id);
    }

    public List<Item> findAll() {
        return entityManager.createQuery("SELECT item FROM Item item", Item.class)
                .getResultList();
    }
}
