package com.grocerybooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.grocerybooking.model.GroceryItem;
import com.grocerybooking.model.OrderItem;
import com.grocerybooking.service.GroceryItemService;
import com.grocerybooking.service.OrderService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private GroceryItemService groceryItemService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/grocery-items")
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemService.getAllItems();
    }

    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder(@RequestBody @Valid List<OrderItem> orderItems, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(getValidationErrors(bindingResult), HttpStatus.BAD_REQUEST);
        }

        orderService.placeOrder(orderItems);
        return new ResponseEntity<>("Order placed successfully", HttpStatus.CREATED);
    }

    private String getValidationErrors(BindingResult bindingResult) {
        List<String> errors = bindingResult.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        return String.join(", ", errors);
    }
}
