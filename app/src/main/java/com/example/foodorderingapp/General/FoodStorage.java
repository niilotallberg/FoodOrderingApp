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
        addFoodDomain(new FoodDomain("Pepperoni pizza", "pepperoni_pizza", "Slices of pepperoni, mozzarella cheese, ground black pepper, tomato sauce, olive oil", 12.50), Category.PIZZA);
        addFoodDomain(new FoodDomain("Margherita pizza", "margherita_pizza", "Mozzarella cheese, ground black pepper, tomato sauce, basilica, olive oil", 11.50), Category.PIZZA);
        addFoodDomain(new FoodDomain("Cheese pizza", "cheese_pizza", "Mozzarella cheese, ground black pepper, tomato sauce, olive oil", 10.50), Category.PIZZA);
        addFoodDomain(new FoodDomain("Vegetable pizza", "vegetable_pizza", "Mozzarella cheese, tomatoes, arugula , salad dressing, ground black pepper, tomato sauce, olive oil", 12.50), Category.PIZZA);
        addFoodDomain(new FoodDomain("Hawaiian pizza", "hawaiian_pizza", "Pineapple, ham, mozzarella cheese, ground black pepper, tomato sauce, olive oil", 12.50), Category.PIZZA);
        addFoodDomain(new FoodDomain("Meat lover pizza", "meat_lover_pizza", "Ham, slices of pepperoni, kebab, mozzarella cheese, ground black pepper, tomato sauce, olive oil", 12.50), Category.PIZZA);

        addFoodDomain(new FoodDomain("Pasta carbonara", "pasta_carbonara", "Spaghetti, bacon, parmesan cheese, garlic, cream sauce", 12.50), Category.PASTA);
        addFoodDomain(new FoodDomain("Pasta bolognese", "pasta_bolognese", "Spaghetti, ground beef, carrots, onion, tomato sauce, garlic, parmesan cheese", 12.50), Category.PASTA);
        addFoodDomain(new FoodDomain("Chicken pasta", "chicken_pasta", "Tagliatelle pasta, chicken, cream sauce, basilica", 12.50), Category.PASTA);
        addFoodDomain(new FoodDomain("Spaghetti aglio e olio", "spaghetti_aglio_e_olio", "Spaghetti, olive oil, garlic, ground black pepper, parsley, Parmigiano-Reggiano cheese, red chili", 12.50), Category.PASTA);


        addFoodDomain(new FoodDomain("Cheese burger", "cheese_burger", "Beef, gouda cheese, special sauce, lettuce, tomatoes, onion rings, ketchup, mustard", 8.50), Category.BURGER);
        addFoodDomain(new FoodDomain("Bacon burger", "bacon_burger", "Beef, gouda cheese, bacon, special sauce, lettuce, tomatoes, ketchup, mustard", 9.50), Category.BURGER);
        addFoodDomain(new FoodDomain("Chicken burger", "chicken_burger", "Chicken, gouda cheese, special sauce, lettuce, tomatoes, ketchup", 9.00), Category.BURGER);
        addFoodDomain(new FoodDomain("Vegetarian burger", "vegetarian_burger", "Vegetarian beef, vegetarian cheese, special sauce, lettuce, tomatoes, onion rings", 11.00), Category.BURGER);
        addFoodDomain(new FoodDomain("Whopper", "whopper_burger", "2x Beef, gouda cheese, mayonnaise, lettuce, tomatoes, ketchup, mustard", 11.00), Category.BURGER);

        addFoodDomain(new FoodDomain("CocaCola", "cocacola", "CocaCola - Normal sugar", 2.50), Category.DRINK);
        addFoodDomain(new FoodDomain("CocaCola Zero", "cocacola_zero", "CocaCola - Zero sugar", 2.50), Category.DRINK);
        addFoodDomain(new FoodDomain("Sprite", "sprite", "Sprite - Normal sugar", 2.50), Category.DRINK);
        addFoodDomain(new FoodDomain("Sprite Zero", "sprite_zero", "Sprite - Zero sugar", 2.50), Category.DRINK);
        addFoodDomain(new FoodDomain("Fanta", "fanta_can", "Fanta - Normal sugar", 2.00), Category.DRINK);
        addFoodDomain(new FoodDomain("Fanta Zero", "fanta_zero", "Fanta - Zero sugar", 2.00), Category.DRINK);

        addFoodDomain(new FoodDomain("Ice Cream", "ice_cream", "Strawberry, vanilla and chocolate ice cream", 4.00), Category.DESSERT);
        addFoodDomain(new FoodDomain("Cheese cake", "cheese_cake", "Cheese cake, Strawberries, blueberries", 5.00), Category.DESSERT);
        addFoodDomain(new FoodDomain("Chocolate cake", "chocolate_cake", "Chocolate cake, chocolate sauce", 5.00), Category.DESSERT);
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