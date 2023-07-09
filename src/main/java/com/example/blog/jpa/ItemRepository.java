package com.example.blog.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemName(String itemName);

    List<Item> findByItemNameContains(String itemName);
    List<Item> findByPriceLessThanEqual(Integer price);
    List<Item> findByItemNameContainsAndPriceLessThanEqual(String itemName, Integer price);

}