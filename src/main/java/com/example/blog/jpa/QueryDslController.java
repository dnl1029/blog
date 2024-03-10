package com.example.blog.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v2")
@RequiredArgsConstructor
@Slf4j
public class QueryDslController {

    private final QueryDslRepository queryDslRepository;

    @GetMapping("queryDsl/findAll")
    public List<QueryDslItemEntity> findAllItem() {
        List<QueryDslItemEntity> entityList = queryDslRepository.findAllItem();
        return entityList;
    }

    @GetMapping("queryDsl/findItemById")

    public List<QueryDslItemEntity> findItemById(Long id) {
        List<QueryDslItemEntity> entityList = queryDslRepository.findItemById(id);
        return entityList;
    }

    @GetMapping("queryDsl/findItemByItemName")
    public List<QueryDslItemEntity> findItemByItemName(String itemName) {
        List<QueryDslItemEntity> entityList = queryDslRepository.findItemByItemName(itemName);
        return entityList;
    }

    @GetMapping("queryDsl/findItemByNameContainsAndPriceLessThanEqual")
    public List<QueryDslItemEntity> findItemByNameContainsAndPriceLessThanEqual(String itemName, Integer price) {
        List<QueryDslItemEntity> entityList = queryDslRepository.findItemByNameContainsAndPriceLessThanEqual(itemName, price);
        return entityList;
    }

}
