package com.example.foodorderingapp.General;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodStorage {
    public enum Category {
        PIZZA,
        PASTA,
        BURGER,
        DRINK,
        DESSERT
    }

    private Map<Category, List<FoodDomain>> foodList;

    public FoodStorage() {
        foodList = new HashMap<>();
        for (Category category : Category.values()) {
            foodList.put(category, new ArrayList<>());
        }
        addFoodDomain(new FoodDomain("Pepperoni pizza", "pepperoni_pizza", "Slices of pepperoni, mozzarella cheese, ground black pepper, tomato sauce, olive oil", 14.50), Category.PIZZA);
        addFoodDomain(new FoodDomain("Margherita pizza", "margherita_pizza", "Mozzarella cheese, ground black pepper, tomato sauce, basilica, olive oil", 14.50), Category.PIZZA);
        addFoodDomain(new FoodDomain("Cheese pizza", "cheese_pizza", "Mozzarella cheese, ground black pepper, tomato sauce, olive oil", 12.50), Category.PIZZA);
        addFoodDomain(new FoodDomain("Vegetable pizza", "vegetable_pizza", "Mozzarella cheese, tomatoes, arugula , salad dressing, ground black pepper, tomato sauce, olive oil", 13.50), Category.PIZZA);

        addFoodDomain(new FoodDomain("Pasta carbonara", "pasta_carbonara", "Spagetti, bacon, parmesan cheese, garlic, cream sauce", 14.50), Category.PASTA);


        addFoodDomain(new FoodDomain("Cheese burger", "cheese_burger", "Beef, gouda cheese, special sauce, lettuce, tomatoes, onion rings", 11.50), Category.BURGER);


        addFoodDomain(new FoodDomain("CocaCola", "cocacola", "CocaCola - Normal sugar", 3.50), Category.DRINK);


        addFoodDomain(new FoodDomain("Ice Cream", "ice_cream", "Strawberry, vanilla and chocolate ice cream", 6.50), Category.DESSERT);
    }

    public void addFoodDomain(FoodDomain foodDomain, Category category) {
        foodList.get(category).add(foodDomain);
    }

    public List<FoodDomain> getFoodListByCategory(Category category) {
        return foodList.get(category);
    }

    public Map<Category, List<FoodDomain>> getAllFoodList() {
        return foodList;
    }
}