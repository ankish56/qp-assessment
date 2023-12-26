package com.grocerybooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocerybooking.model.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {

}
