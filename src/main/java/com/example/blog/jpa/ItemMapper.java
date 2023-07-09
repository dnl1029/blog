package com.example.blog.jpa;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    List<Item> findAll();

    Optional<Item> findById(LinkedHashMap<String,Object> paramMap);

    boolean existsById(LinkedHashMap<String,Object> paramMap);

    List<Item> findByItemName(LinkedHashMap<String,Object> paramMap);

    List<Item> findByItemNameLike(LinkedHashMap<String,Object> paramMap);

    List<Item> findByPriceLessThanEqual(LinkedHashMap<String,Object> paramMap);

    List<Item> findByItemNameLikeAndPriceLessThanEqual(LinkedHashMap<String,Object> paramMap);

    void save(LinkedHashMap<String,Object> paramMap);
//    void saveAll(LinkedHashMap<String,Object> paramMap);

    void update(LinkedHashMap<String,Object> paramMap);

    void deleteById(LinkedHashMap<String,Object> paramMap);

//    void deleteAllByIdInBatch(LinkedHashMap<String,Object> paramMap);
}
