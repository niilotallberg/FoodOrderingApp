package com.example.foodorderingapp.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Adaptor.FavoritesAdapter;
import com.example.foodorderingapp.Domain.FoodDomain;
import com.example.foodorderingapp.General.User;
import com.example.foodorderingapp.General.UserAuthenticator;
import com.example.foodorderingapp.R;

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

        User currentUser = UserAuthenticator.getInstance().getAuthenticatedUser();
        favoriteProducts = currentUser.getFavoriteFoods();

        favoritesAdapter = new FavoritesAdapter(favoriteProducts);
        recyclerView.setAdapter(favoritesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        favoritesAdapter.notifyDataSetChanged();
    }
}