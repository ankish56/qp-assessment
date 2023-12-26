package com.grocerybooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocerybooking.model.GroceryItem;
import com.grocerybooking.model.InventoryUpdateRequest;
import com.grocerybooking.service.GroceryItemService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private GroceryItemService groceryItemService;

	@PostMapping("/grocery-items")
	public ResponseEntity<Void> addGroceryItem(@RequestBody GroceryItem item) {
		groceryItemService.addItem(item);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/grocery-items")
	public List<GroceryItem> getAllGroceryItems() {
		return groceryItemService.getAllItems();
	}

	@PutMapping("/grocery-items/{itemId}")
	public ResponseEntity<Void> updateGroceryItem(@PathVariable Long itemId, @RequestBody GroceryItem updatedItem) {
		groceryItemService.updateItem(itemId, updatedItem);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/grocery-items/{itemId}")
	public ResponseEntity<Void> removeGroceryItem(@PathVariable Long itemId) {
		groceryItemService.removeItem(itemId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/inventory/{itemId}")
	public ResponseEntity<String> updateInventory(@PathVariable Long itemId,
			@RequestBody @Valid InventoryUpdateRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(getValidationErrors(bindingResult), HttpStatus.BAD_REQUEST);
		}

		groceryItemService.updateInventory(itemId, request.getQuantity());
		return new ResponseEntity<>("Inventory updated successfully", HttpStatus.OK);
	}

	private String getValidationErrors(BindingResult bindingResult) {
		List<String> errors = bindingResult.getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());
		return String.join(", ", errors);
	}

}
