package com.example.blog.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class QueryDslRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public QueryDslRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    /**
     * 1. R / findAll()
     * select i from Item i
     */
    public List<QueryDslItemEntity> findAllItem() {
        QQueryDslItemEntity entity = QQueryDslItemEntity.queryDslItemEntity;
        List<QueryDslItemEntity> entityList = query.select(entity)
                .from(entity)
                .fetch();
        return entityList;
    }

    /**
     * 2. R / findById()
     * select i from Item i where i.id = ?
     * jpa --> Optional<Item> result = itemRepository.findById(id);
     */
    public List<QueryDslItemEntity> findItemById(Long id) {
        QQueryDslItemEntity entity = QQueryDslItemEntity.queryDslItemEntity;
        List<QueryDslItemEntity> entityList = query.select(entity)
                .from(entity)
                .where(entity.id.eq(id))
                .fetch();
        return entityList;
    }

    /**
     * 4. R / findByItemName
     * select i from Item i where i.itemName = ?
     * jpa --> List<Item> result = itemRepository.findByItemName(itemName);
     */
    public List<QueryDslItemEntity> findItemByItemName(String itemName) {
        QQueryDslItemEntity entity = QQueryDslItemEntity.queryDslItemEntity;
        List<QueryDslItemEntity> entityList = query.select(entity)
                .from(entity)
                .where(entity.itemName.eq(itemName))
                .fetch();
        return entityList;
    }

    /**
     * 7. R / findByPriceLessThanEqual()
     * select i from Item i where i.itemName like ? and i.price <= ?
     * jpa --> List<Item> result = itemRepository.findByItemNameContainsAndPriceLessThanEqual(itemName, price);
     */
     public List<QueryDslItemEntity> findItemByNameContainsAndPriceLessThanEqual(String itemName, Integer price) {
         QQueryDslItemEntity entity = QQueryDslItemEntity.queryDslItemEntity;
         /*
         queryDsl where절 예시.
         * like 'abc'
         * contains '%abc%'
         * lt <
         * loe <=
         * gt >
         * goe >=
        */
         List<QueryDslItemEntity> entityList = query.select(entity)
                 .from(entity)
                 .where(entity.itemName.contains(itemName).and(entity.price.loe(price)))
                 .fetch();
         return entityList;
     }

}
