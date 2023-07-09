package com.example.blog.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v2")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemJpaService itemJpaService;
    private final ItemMybatisService itemMybatisService;

    @GetMapping("test/findAll")
    public void findAll() {
        List<Item> result = itemJpaService.findAll();
        log.info("findAll / jpa :{}",result);
        List<Item> result2 = itemMybatisService.findAll();
        log.info("findAll / mybatis :{}",result2);
    }

    @GetMapping("test/findById")
    public void findById(Long id) {
        Optional<Item> result = itemJpaService.findById(id);
        log.info("findById / jpa :{}",result);
        Optional<Item> result2 = itemMybatisService.findById(id);
        log.info("findById / mybatis :{}",result2);
    }

    @GetMapping("test/existsById")
    public void existsById(Long id){
        boolean result = itemJpaService.existsById(id);
        log.info("existsById / jpa :{}",result);
        boolean result2 = itemMybatisService.existsById(id);
        log.info("existsById / mybatis :{}",result2);
    }

    @GetMapping("test/findByItemName")
    public void findByItemName(String itemName) {
        List<Item> result = itemJpaService.findByItemName(itemName);
        log.info("findByItemName / jpa :{}",result);
        List<Item> result2 = itemMybatisService.findByItemName(itemName);
        log.info("findByItemName / mybatis :{}",result2);
    }

    @GetMapping("test/findByItemNameLike")
    public void findByItemNameLike(String itemName) {
        List<Item> result = itemJpaService.findByItemNameLike(itemName);
        log.info("findByItemNameLike / jpa :{}",result);
        List<Item> result2 = itemMybatisService.findByItemNameLike(itemName);
        log.info("findByItemNameLike / mybatis :{}",result2);
    }


    @GetMapping("test/findByPriceLessThanEqual")
    public void findByPriceLessThanEqual(Integer price) {
        List<Item> result = itemJpaService.findByPriceLessThanEqual(price);
        log.info("findByPriceLessThanEqual / jpa :{}",result);
        List<Item> result2 = itemMybatisService.findByPriceLessThanEqual(price);
        log.info("findByPriceLessThanEqual / mybatis :{}",result2);
    }

    @GetMapping("test/findByItemNameLikeAndPriceLessThanEqual")
    public void findByItemNameLikeAndPriceLessThanEqual(String itemName, Integer price) {
        List<Item> result = itemJpaService.findByItemNameLikeAndPriceLessThanEqual(itemName, price);
        log.info("findByItemNameLikeAndPriceLessThanEqual / jpa :{}",result);
        List<Item> result2 = itemMybatisService.findByItemNameLikeAndPriceLessThanEqual(itemName, price);
        log.info("findByItemNameLikeAndPriceLessThanEqual / mybatis :{}",result2);
    }

    @PostMapping("test/save")
    public void save(@RequestBody Item item) {
        Item result = itemJpaService.save(item);
        log.info("save / jpa : {}",result);
        itemMybatisService.save(item);
        //log.info("save / mybatis :{}",result2);
    }

    @PostMapping("test/saveAll")
    public void saveAll(@RequestBody ItemList itemList) {
        List<Item> result = itemJpaService.saveAll(itemList);
        log.info("saveAll / jpa : {}",result);
        itemMybatisService.saveAll(itemList);
    }

    @PostMapping("test/update")
    public void update(@RequestBody Item item) {
        Item result = itemJpaService.update(item);
        log.info("saveAll / jpa : {}",result);
        //update는 중복이라 동작 확인후 주석처리
        //itemMybatisService.update(item);
    }

    @PostMapping("test/deleteById")
    public void deleteById(Long id) {
        itemJpaService.deleteById(id);
        //delete는 중복이라 동작 확인후 주석처리
        //itemMybatisService.deleteById(id);
    }

    @PostMapping("test/deleteAll")
    public void deleteAll(IdList ids) {
        itemJpaService.deleteAllByIdInBatch(ids);
        //delete는 중복이라 동작 확인후 주석처리
        //itemMybatisService.deleteAllByIdInBatch(ids);
    }

}
