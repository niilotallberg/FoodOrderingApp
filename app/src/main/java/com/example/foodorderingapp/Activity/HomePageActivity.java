// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.foodorderingapp.Adapter.CategoryAdapter;
import com.example.foodorderingapp.Adapter.PopularAdapter;
import com.example.foodorderingapp.General.CategoryDomain;
import com.example.foodorderingapp.General.FoodDomain;
import com.example.foodorderingapp.General.FoodStorage;
import com.example.foodorderingapp.General.User;
import com.example.foodorderingapp.Helpers.UserAuthenticator;
import com.example.foodorderingapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    private ImageView ivProfilePicture;
    private TextView txtWelcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        User currentUser = UserAuthenticator.getInstance().getAuthenticatedUser();
        txtWelcomeText = findViewById(R.id.txtWelcomeText);
        txtWelcomeText.setText(String.format("Hi '%s'", currentUser.getUsername()));

        ivProfilePicture = findViewById(R.id.ivProfilePicture);
        ivProfilePicture.setImageResource(currentUser.getProfilePicture());

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    @Override
    protected void onResume() {
        super.onResume();

        User currentUser = UserAuthenticator.getInstance().getAuthenticatedUser();
        if (currentUser != null) {
            ivProfilePicture.setImageResource(currentUser.getProfilePicture());
            txtWelcomeText.setText(String.format("Hi %s", currentUser.getUsername()));
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back, please place your order or logout from profile settings", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(HomePageActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, HomePageActivity.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, ProfileSettingsActivity.class));
            }
        });

        favoritesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, FavoritesActivity.class));
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, FaqActivity.class));
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.rvCategories);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pizza", "pizza"));
        category.add(new CategoryDomain("Pasta", "pasta"));
        category.add(new CategoryDomain("Burger", "burger"));
        category.add(new CategoryDomain("Drink", "drink"));
        category.add(new CategoryDomain("Dessert", "dessert"));

        adapter = new CategoryAdapter(category, new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(HomePageActivity.this, DetailCategoryActivity.class);
                intent.putExtra("category", FoodStorage.Category.valueOf(category.get(position).getTitle().toUpperCase()));
                startActivity(intent);
            }
        });

        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.rvPopular);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Pepperoni pizza", "pepperoni_pizza", "Slices of pepperoni, mozzarella cheese, ground black pepper, tomato sauce", 14.50));
        foodList.add(new FoodDomain("Cheese burger", "cheese_burger", "Beef, gouda cheese, special sauce, lettuce, tomatoes, onion rings", 11.50));
        foodList.add(new FoodDomain("Pasta carbonara", "pasta_carbonara", "Spagetti, bacon, parmesan cheese, garlic, cream sauce", 14.50));
        foodList.add(new FoodDomain("CocaCola", "cocacola", "CocaCola - Normal sugar", 3.50));
        foodList.add(new FoodDomain("Ice Cream", "ice_cream", "Strawberry, vanilla and chocolate ice cream", 6.50));

        adapter2 = new PopularAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter2);
    }
}