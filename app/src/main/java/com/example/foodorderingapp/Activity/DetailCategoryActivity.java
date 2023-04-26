package com.example.foodorderingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.foodorderingapp.Adapter.DetailCategoryAdapter;
import com.example.foodorderingapp.General.FoodDomain;
import com.example.foodorderingapp.General.FoodStorage;
import com.example.foodorderingapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DetailCategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DetailCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);

        Intent intent = getIntent();
        FoodStorage.Category category = (FoodStorage.Category) intent.getSerializableExtra("category");

        recyclerView = findViewById(R.id.category_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FoodStorage foodStorage = new FoodStorage();
        List<FoodDomain> foodList = foodStorage.getFoodListByCategory(category);
        adapter = new DetailCategoryAdapter(foodList);
        recyclerView.setAdapter(adapter);

        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.btnCartView);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        LinearLayout favoritesBtn = findViewById(R.id.favoritesBtn);
        LinearLayout chatBtn = findViewById(R.id.chatBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailCategoryActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailCategoryActivity.this, HomePageActivity.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailCategoryActivity.this, ProfileSettingsActivity.class));
            }
        });

        favoritesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailCategoryActivity.this, FavoritesActivity.class));
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailCategoryActivity.this, FaqActivity.class));
            }
        });
    }
}