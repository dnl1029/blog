package com.example.blog.jpa;

import com.example.blog.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemMybatisService {

    private final ItemMapper itemMapper;

    /**
     * 1. R / findAll()
     * select i from Item i
     */
    public List<Item> findAll() {
        List<Item> result = itemMapper.findAll();
        return result;
    }

    /**
     * 2. R / findById()
     * select i from Item i where i.id = ?
     */
    public Optional<Item> findById(Long id) {
        LinkedHashMap<String,Object> paramMap = new LinkedHashMap<>();
        paramMap.put("ID",id);
        Optional<Item> result = itemMapper.findById(paramMap);
        return result;
    }

    /**
     * 3. R / existsById()
     *
     */
    public boolean existsById(Long id){
        LinkedHashMap<String,Object> paramMap = new LinkedHashMap<>();
        paramMap.put("ID",id);
        boolean result = itemMapper.existsById(paramMap);
        return result;
    }

    /**
     * 4. R / findByItemName
     * select i from Item i where i.itemName = ?
     */
    public List<Item> findByItemName(String itemName) {
        LinkedHashMap<String,Object> paramMap = new LinkedHashMap<>();
        paramMap.put("ITEM_NAME",itemName);
        List<Item> result = itemMapper.findByItemName(paramMap);
        return result;
    }

    /**
     * 5. R / findByItemNameLike()
     * select i from Item i where i.name like ?
     */
    public List<Item> findByItemNameLike(String itemName) {
        LinkedHashMap<String,Object> paramMap = new LinkedHashMap<>();
        paramMap.put("ITEM_NAME",itemName);
        List<Item> result = itemMapper.findByItemNameLike(paramMap);
        return result;
    }

    /**
     * 6. R / findByPriceLessThanEqual()
     * select i from Item i where i.price <= ?
     */
    public List<Item> findByPriceLessThanEqual(Integer price) {
        LinkedHashMap<String,Object> paramMap = new LinkedHashMap<>();
        paramMap.put("PRICE",price);
        List<Item> result = itemMapper.findByPriceLessThanEqual(paramMap);
        return result;
    }

    /**
     * 7. R / findByPriceLessThanEqual()
     * select i from Item i where i.itemName like ? and i.price <= ?
     */
    public List<Item> findByItemNameLikeAndPriceLessThanEqual(String itemName, Integer price) {
        LinkedHashMap<String,Object> paramMap = new LinkedHashMap<>();
        paramMap.put("ITEM_NAME",itemName);
        paramMap.put("PRICE",price);
        List<Item> result = itemMapper.findByItemNameLikeAndPriceLessThanEqual(paramMap);
        return result;
    }

    /**
     * 8. C / save()
     */
    public void save(Item item) {
        LinkedHashMap<String,Object> paramMap = new LinkedHashMap<>();
        paramMap.put("ITEM_NAME",item.getItemName());
        paramMap.put("PRICE",item.getPrice());
        paramMap.put("QUANTITY",item.getQuantity());
        log.info("paramMap : {}",paramMap);
        itemMapper.save(paramMap);
    }

    /**
     * 9. C / saveAll()
     */
    public void saveAll(ItemList itemList) {
        List<Item> items = itemList.getItemList();
        items.stream().forEach(item -> save(item));
    }

    /**
     * 10. U / update()
     */
    public void update(Item item) {
        LinkedHashMap<String,Object> paramMap = new LinkedHashMap<>();
        paramMap.put("ID",item.getId());
        paramMap.put("ITEM_NAME",item.getItemName());
        paramMap.put("PRICE",item.getPrice());
        paramMap.put("QUANTITY",item.getQuantity());
        itemMapper.update(paramMap);
    }

    /**
     * 11. D / deleteById()
     */
    public void deleteById(Long id) {
        LinkedHashMap<String,Object> paramMap = new LinkedHashMap<>();
        paramMap.put("ID",id);
        itemMapper.deleteById(paramMap);
    }

    /**
     * 12. D / deleteAllByIdInBatch()
     */
    public void deleteAllByIdInBatch(IdList ids) {
        List<Long> deleteList = ids.getIds();
        deleteList.stream().forEach(id -> deleteById(id));
    }

}
