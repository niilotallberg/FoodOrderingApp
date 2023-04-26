package com.example.foodorderingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.example.foodorderingapp.Adapter.DetailCategoryAdapter;
import com.example.foodorderingapp.General.FoodDomain;
import com.example.foodorderingapp.General.FoodStorage;
import com.example.foodorderingapp.R;
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
    }
}