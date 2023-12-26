package com.grocerybooking.service;

import com.grocerybooking.exception.CustomException;
import com.grocerybooking.model.GroceryItem;
import com.grocerybooking.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public List<GroceryItem> getAllItems() {
        return groceryItemRepository.findAll();
    }

    public void addItem(GroceryItem item) {
        groceryItemRepository.save(item);
    }

    public void updateItem(Long itemId, GroceryItem updatedItem) {
        Optional<GroceryItem> existingItem = groceryItemRepository.findById(itemId);
        if (existingItem.isPresent()) {
            GroceryItem currentItem = existingItem.get();
            groceryItemRepository.save(currentItem);
        } else {
            throw new CustomException("Grocery item not found with ID: " + itemId);
        }
    }

    public void removeItem(Long itemId) {
        groceryItemRepository.deleteById(itemId);
    }

    public void updateInventory(Long itemId, int quantityChange) {
        Optional<GroceryItem> existingItem = groceryItemRepository.findById(itemId);
        if (existingItem.isPresent()) {
            GroceryItem currentItem = existingItem.get();
            int updatedQuantity = currentItem.getQuantity() + quantityChange;
            if (updatedQuantity >= 0) {
                currentItem.setQuantity(updatedQuantity);
                groceryItemRepository.save(currentItem);
            } else {
                throw new CustomException("Inventory update failed. Not enough items in stock.");
            }
        } else {
            throw new CustomException("Grocery item not found with ID: " + itemId);
        }
    }
}
