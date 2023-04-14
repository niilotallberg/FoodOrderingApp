package com.example.foodorderingapp.General;

import com.example.foodorderingapp.Domain.FoodDomain;

public class CartItem {
    private FoodDomain foodDomain;
    private int quantity;

    public CartItem(FoodDomain foodDomain, int quantity) {
        this.foodDomain = foodDomain;
        this.quantity = quantity;
    }

    public FoodDomain getFoodDomain() {
        return foodDomain;
    }

    public void setFoodDomain(FoodDomain foodDomain) {
        this.foodDomain = foodDomain;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}