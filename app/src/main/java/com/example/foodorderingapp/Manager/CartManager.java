// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.Manager;

import android.content.Context;
import com.example.foodorderingapp.Data.Serializer;
import com.example.foodorderingapp.General.FoodDomain;
import com.example.foodorderingapp.General.User;

import java.util.HashMap;

public class CartManager {
    private HashMap<FoodDomain, Integer> cart;
    private User cartOwner;
    private static CartManager instance;

    private CartManager() {}

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void initializeCart(Context context, User cartOwner) { // Load the users current cart from a serialized file
        this.cartOwner = cartOwner;
        final HashMap<FoodDomain, Integer> cart = Serializer.deserializeCart(context, this.cartOwner.getUsername() + "_cart.ser");
        if (cart == null) {
            this.cart = new HashMap<>();
        } else {
            this.cart = cart;
        }
    }

    private void saveCart(Context context){ // Saves the users  cart to a file
        Serializer.serializeObject(cart, context, this.cartOwner.getUsername() + "_cart.ser");
    }


    public void addCartItem(Context context, FoodDomain foodDomain, Integer quantity) { // Handles the case where a user adds something to cart
        FoodDomain existingItem = null;

        for (FoodDomain item : cart.keySet()) {
            if (item.getTitle().equals(foodDomain.getTitle())) {
                existingItem = item;
                break;
            }
        }

        if (existingItem != null) {
            Integer currentQuantity = cart.get(existingItem);
            int newQuantity = currentQuantity + quantity;
            if (newQuantity <= 0) {
                cart.remove(existingItem);
            } else {
                cart.put(existingItem, newQuantity);
            }
        } else if (quantity > 0) {
            cart.put(foodDomain, quantity);
        }

        saveCart(context);
    }

    public void removeCartItem(Context context, FoodDomain foodDomain) { // Handles the case where a user wants to decrease the quantity of an item in cart or remove it completely
        if (cart.containsKey(foodDomain)) {
            if (cart.get(foodDomain) > 1) {
                int newValue = cart.get(foodDomain) - 1;
                cart.put(foodDomain, newValue);
            }
            else {
                cart.remove(foodDomain);
            }
            saveCart(context);
        }
    }

    public HashMap<FoodDomain, Integer> getCartItems() {
        return cart;
    }

    public void clearCart(Context context) { // This method will empty the cart completely from everything
        cart.clear();
        saveCart(context);
    }
}