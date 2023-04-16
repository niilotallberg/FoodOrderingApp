package com.example.foodorderingapp.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Adaptor.FavoritesAdapter;
import com.example.foodorderingapp.Domain.FoodDomain;
import com.example.foodorderingapp.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoritesAdapter favoritesAdapter;
    private List<FoodDomain> favoriteProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        recyclerView = findViewById(R.id.favoritesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add favorite products to the favoriteProducts list
        favoriteProducts = new ArrayList<>();
        favoriteProducts.add(new FoodDomain("Pepperoni pizza", "pepperoni_pizza", "Slices of pepperoni, mozzarella cheese, ground black pepper, tomato sauce", 14.50));
        favoriteProducts.add(new FoodDomain("Cheese burger", "cheese_burger", "Beef, gouda cheese, special sauce, lettuce, tomatoes, onion rings", 11.50));
        favoriteProducts.add(new FoodDomain("Pasta carbonara", "pasta_carbonara", "Spagetti, bacon, parmesan cheese, garlic, cream sauce", 14.50));

        // Set up the adapter with the favoriteProducts list
        favoritesAdapter = new FavoritesAdapter(favoriteProducts);
        recyclerView.setAdapter(favoritesAdapter);
    }
}