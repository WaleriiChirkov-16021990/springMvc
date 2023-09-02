package org.chirkov.firstSpringMvcProject.services;

import jakarta.transaction.Transactional;

import org.chirkov.firstSpringMvcProject.models.Item;
import org.chirkov.firstSpringMvcProject.models.Person;
import org.chirkov.firstSpringMvcProject.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//@Transactional(readOnly = true)


@Service
@Transactional
public class ItemService {
    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> findItemsByItemName(String itemName) {
        return itemsRepository.findItemsByItemName(itemName);
    }

    public List<Item> findItemsByOwner(Person owner) {
        return itemsRepository.findItemsByOwner(owner);
    }

    public List<Item> findAll() {
        return itemsRepository.findAll();
    }

    public Optional<Item> findOne(int id) {
        return itemsRepository.findById(id);
    }

//    @Transactional
    public void save(Item item) {
        itemsRepository.save(item);
    }

//    @Transactional
    public void update(int id, Item itemUpdate) {
        itemUpdate.setId(id);
        itemsRepository.save(itemUpdate);
    }

//    @Transactional
    public void delete(int id) {
        itemsRepository.deleteById(id);
    }

//    @Transactional
    public void delete(Item item) {
        itemsRepository.delete(item);
    }

    public ItemsRepository getItemsRepository() {
        return itemsRepository;
    }
}
