package com.example.foodorderingapp.General;

import java.util.HashMap;

public class Cart {
    private HashMap<String, CartItem> cartItems;
    private User user;

    public Cart(HashMap<String, CartItem> cartItems, User user) {
        this.cartItems = cartItems;
        this.user = user;
    }

    public HashMap<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(HashMap<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
