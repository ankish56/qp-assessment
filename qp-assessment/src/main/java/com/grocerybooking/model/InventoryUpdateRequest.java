package com.grocerybooking.model;

import javax.validation.constraints.Positive;

public class InventoryUpdateRequest {


    @Positive(message = "Quantity must be a positive value")
    private int quantity;

    public InventoryUpdateRequest() {
    }

    public InventoryUpdateRequest(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}