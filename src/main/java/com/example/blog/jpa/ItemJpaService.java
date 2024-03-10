package com.example.blog.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemJpaService {

    private final ItemRepository itemRepository;

    /**
     * 1. R / findAll()
     * select i from Item i
     * JpaRepository가 제공하므로, Repository에 별도 선언 필요없음
     */
    public List<Item> findAll() {
        List<Item> result = itemRepository.findAll();
        return result;
    }

    /**
     * 2. R / findById()
     * select i from Item i where i.id = ?
     * JpaRepository가 제공하므로, Repository에 별도 선언 필요없음
     */
    public Optional<Item> findById(Long id) {
        Optional<Item> result = itemRepository.findById(id);
        return result;
    }

    /**
     * 3. R / existsById()
     *
     * JpaRepository가 제공하므로, Repository에 별도 선언 필요없음
     */
    public boolean existsById(Long id){
        boolean result = itemRepository.existsById(id);
        return result;
    }

    /**
     * 4. R / findByItemName
     * select i from Item i where i.itemName = ?
     * Repository에 별도 선언 필요
     */
    public List<Item> findByItemName(String itemName) {
        List<Item> result = itemRepository.findByItemName(itemName);
        return result;
    }

    /**
     * 5. R / findByItemNameLike()
     * select i from Item i where i.name like ?
     * Repository에 별도 선언 필요
     */
    public List<Item> findByItemNameLike(String itemName) {
        //List<Item> result = itemRepository.findByItemNameLike(itemName);
        List<Item> result = itemRepository.findByItemNameContains(itemName);
        return result;
    }

    /**
     * 6. R / findByPriceLessThanEqual()
     * select i from Item i where i.price <= ?
     * Repository에 별도 선언 필요
     */
    public List<Item> findByPriceLessThanEqual(Integer price) {
        List<Item> result = itemRepository.findByPriceLessThanEqual(price);
        return result;
    }

    /**
     * 7. R / findByItemNameLikeAndPriceLessThanEqual()
     * select i from Item i where i.itemName like ? and i.price <= ?
     * Repository에 별도 선언 필요
     */
    public List<Item> findByItemNameLikeAndPriceLessThanEqual(String itemName, Integer price) {
        //List<Item> result = itemRepository.findByItemNameLikeAndPriceLessThanEqual(itemName, price);
        List<Item> result = itemRepository.findByItemNameContainsAndPriceLessThanEqual(itemName, price);
        return result;
    }

    /**
     * 8. C / save()
     * JpaRepository가 제공하므로, Repository에 별도 선언 필요없음
     */
    public Item save(Item item) {
        Item result = itemRepository.save(item);
        return result;
    }

    /**
     * 9. C / saveAll()
     * JpaRepository가 제공하므로, Repository에 별도 선언 필요없음
     */
    public List<Item> saveAll(ItemList itemList) {
        List<Item> items = itemList.getItemList();
        List<Item> result = itemRepository.saveAll(items);
        return result;
    }

    /**
     * 10. U / update()
     * JpaRepository가 제공하므로, Repository에 별도 선언 필요없음
     */
    public Item update(Item item) {
        Optional<Item> oldItem = itemRepository.findById(item.getId());

        //oldItem.get().setId(item.getId());
        oldItem.get().setItemName(item.getItemName());
        oldItem.get().setPrice(item.getPrice());
        oldItem.get().setQuantity(item.getQuantity());
        Item result = itemRepository.save(oldItem.get());
        return result;
    }

    /**
     * 11. D / deleteById()
     * JpaRepository가 제공하므로, Repository에 별도 선언 필요없음
     */
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    /**
     * 12. D / deleteAllByIdInBatch()
     * JpaRepository가 제공하므로, Repository에 별도 선언 필요없음
     */
    public void deleteAllByIdInBatch(IdList ids) {
        itemRepository.deleteAllByIdInBatch(ids.getIds());
    }

}
