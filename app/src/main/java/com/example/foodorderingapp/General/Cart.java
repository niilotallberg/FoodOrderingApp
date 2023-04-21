package com.example.foodorderingapp.General;

import android.content.Context;

import com.example.foodorderingapp.Data.Serializer;
import com.example.foodorderingapp.Domain.FoodDomain;

import java.util.HashMap;

public class Cart {
    private HashMap<FoodDomain, Integer> cart;
    private User cartOwner;
    private static Cart instance;

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void intializeCart(Context context, User cartOwner) {
        this.cartOwner = cartOwner;
        final HashMap<FoodDomain, Integer> cart = Serializer.deserializeCart(context, this.cartOwner.getUsername());
        if (cart == null) {
            this.cart = new HashMap<>();
        }
    }

    private void saveCart(Context context){
        Serializer.serializeObject(cart, context, this.cartOwner.getUsername());
    }

    public void addCartItem(Context context, FoodDomain foodDomain, Integer quantity) {
        // TODO ADD THE FUNCTIONALITY
        // TODO ADD THE RIGHT QUANTITY TO CART AND SAVE
    }

    private Cart() {}
}
