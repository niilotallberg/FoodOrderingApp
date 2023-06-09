// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.General;

import com.example.foodorderingapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodStorage {
    public enum Category { // Here we create the different categories used in the program
        PIZZA("pizza", R.drawable.cat_background1),
        PASTA("pasta", R.drawable.cat_background2),
        BURGER("burger", R.drawable.cat_background3),
        DRINK("drink", R.drawable.cat_background4),
        DESSERT("dessert", R.drawable.cat_background5);

        private final String iconName;
        private final int backgroundDrawableId;

        Category(String iconName, int backgroundDrawableId) {
            this.iconName = iconName;
            this.backgroundDrawableId = backgroundDrawableId;
        }

        public String getIconName() {
            return iconName;
        }

        public int getBackgroundDrawableId() {
            return backgroundDrawableId;
        }
    }


    private Map<Category, List<FoodDomain>> foodList;

    public FoodStorage() { // Here we have stored all the different FoodDomains used in our program
        foodList = new HashMap<>();
        for (Category category : Category.values()) {
            foodList.put(category, new ArrayList<>());
        }
        addFoodDomain(new FoodDomain("Pepperoni pizza", "pepperoni_pizza", "Slices of pepperoni, mozzarella cheese, ground black pepper, tomato sauce, olive oil", 12.50), Category.PIZZA);
        addFoodDomain(new FoodDomain("Margherita pizza", "margherita_pizza", "Mozzarella cheese, ground black pepper, tomato sauce, basilica, olive oil", 11.50, true), Category.PIZZA);
        addFoodDomain(new FoodDomain("Cheese pizza", "cheese_pizza", "Mozzarella cheese, ground black pepper, tomato sauce, olive oil", 10.50), Category.PIZZA);
        addFoodDomain(new FoodDomain("Vegetable pizza", "vegetable_pizza", "Mozzarella cheese, tomatoes, arugula , salad dressing, ground black pepper, tomato sauce, olive oil", 12.50), Category.PIZZA);
        addFoodDomain(new FoodDomain("Hawaiian pizza", "hawaiian_pizza", "Pineapple, ham, mozzarella cheese, ground black pepper, tomato sauce, olive oil", 12.50), Category.PIZZA);
        addFoodDomain(new FoodDomain("Meat lover pizza", "meat_lover_pizza", "Ham, slices of pepperoni, kebab, mozzarella cheese, ground black pepper, tomato sauce, olive oil", 12.50), Category.PIZZA);

        addFoodDomain(new FoodDomain("Pasta carbonara", "pasta_carbonara", "Spaghetti, bacon, parmesan cheese, garlic, cream sauce", 12.50), Category.PASTA);
        addFoodDomain(new FoodDomain("Pasta bolognese", "pasta_bolognese", "Spaghetti, ground beef, carrots, onion, tomato sauce, garlic, parmesan cheese", 12.50, true), Category.PASTA);
        addFoodDomain(new FoodDomain("Chicken pasta", "chicken_pasta", "Tagliatelle pasta, chicken, cream sauce, basilica", 12.50), Category.PASTA);
        addFoodDomain(new FoodDomain("Spaghetti aglio e olio", "spaghetti_aglio_e_olio", "Spaghetti, olive oil, garlic, ground black pepper, parsley, Parmigiano-Reggiano cheese, red chili", 12.50), Category.PASTA);


        addFoodDomain(new FoodDomain("Cheese burger", "cheese_burger", "Beef, gouda cheese, special sauce, lettuce, tomatoes, onion rings, ketchup, mustard", 8.50), Category.BURGER);
        addFoodDomain(new FoodDomain("Bacon burger", "bacon_burger", "Beef, gouda cheese, bacon, special sauce, lettuce, tomatoes, ketchup, mustard", 9.50), Category.BURGER);
        addFoodDomain(new FoodDomain("Chicken burger", "chicken_burger", "Chicken, gouda cheese, special sauce, lettuce, tomatoes, ketchup", 9.00), Category.BURGER);
        addFoodDomain(new FoodDomain("Vegetarian burger", "vegetarian_burger", "Vegetarian beef, vegetarian cheese, special sauce, lettuce, tomatoes, onion rings", 11.00), Category.BURGER);
        addFoodDomain(new FoodDomain("Whopper", "whopper_burger", "2x Beef, gouda cheese, mayonnaise, lettuce, tomatoes, ketchup, mustard", 11.00, true), Category.BURGER);

        addFoodDomain(new FoodDomain("CocaCola", "cocacola", "CocaCola - Normal sugar", 2.50), Category.DRINK);
        addFoodDomain(new FoodDomain("CocaCola Zero", "cocacola_zero", "CocaCola - Zero sugar", 2.50, true), Category.DRINK);
        addFoodDomain(new FoodDomain("Sprite", "sprite", "Sprite - Normal sugar", 2.50), Category.DRINK);
        addFoodDomain(new FoodDomain("Sprite Zero", "sprite_zero", "Sprite - Zero sugar", 2.50), Category.DRINK);
        addFoodDomain(new FoodDomain("Fanta", "fanta_can", "Fanta - Normal sugar", 2.00), Category.DRINK);
        addFoodDomain(new FoodDomain("Fanta Zero", "fanta_zero", "Fanta - Zero sugar", 2.00), Category.DRINK);

        addFoodDomain(new FoodDomain("Ice Cream", "ice_cream", "Strawberry, vanilla and chocolate ice cream", 4.00), Category.DESSERT);
        addFoodDomain(new FoodDomain("Cheese cake", "cheese_cake", "Cheese cake, Strawberries, blueberries", 5.00), Category.DESSERT);
        addFoodDomain(new FoodDomain("Chocolate cake", "chocolate_cake", "Chocolate cake, chocolate sauce", 5.00, true), Category.DESSERT);
        addFoodDomain(new FoodDomain("Pannacotta", "pannacotta", "Pannacotta, strawberry jam, blueberries", 6.00), Category.DESSERT);
        addFoodDomain(new FoodDomain("Apple pie", "applepie", "Pannacotta, apple jam, vanilla ice cream", 5.50), Category.DESSERT);

    }

    public void addFoodDomain(FoodDomain foodDomain, Category category) { // This is a method that can be used to add new FoodDomain objects for use in this program
        foodList.get(category).add(foodDomain);
    }

    public List<FoodDomain> getFoodListByCategory(Category category) { // This method returns a list that contains foodDomains from only one, chosen, category
        return foodList.get(category);
    }

    public List<FoodDomain> getPopularFoodList() { // This method return all the FoodDomains that the programmer has set to be Popular (isPopular)
        List<FoodDomain> popularFoodList = new ArrayList<>();

        for (Category category : foodList.keySet()) {
            for (FoodDomain foodDomain : foodList.get(category)) {
                if (foodDomain.isPopular()) {
                    popularFoodList.add(foodDomain);
                }
            }
        }
        return popularFoodList;
    }
}