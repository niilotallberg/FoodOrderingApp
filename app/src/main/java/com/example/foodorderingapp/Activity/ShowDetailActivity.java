package com.example.foodorderingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodorderingapp.Domain.FoodDomain;
import com.example.foodorderingapp.General.User;
import com.example.foodorderingapp.General.UserAuthenticator;
import com.example.foodorderingapp.R;

import java.util.ArrayList;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView btnAddToCart;
    private TextView txtDetailTitle, txtDetailPrice, txtAmount, txtDescription;
    private ImageView ivMinusButton, ivPlusButton, ivDetailPic, btnAddToFavorites;
    private FoodDomain object;
    private int orderAmount = 1;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        initView();
        getBundle();

        btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User currentUser = UserAuthenticator.getInstance().getAuthenticatedUser();
                if (currentUser.getFavoriteFoods() == null) {
                    currentUser.setFavoriteFoods(new ArrayList<FoodDomain>());
                }

                boolean isAlreadyFavorite = false;
                int favoriteIndex = -1;
                for (int i = 0; i < currentUser.getFavoriteFoods().size(); i++) {
                    FoodDomain food = currentUser.getFavoriteFoods().get(i);
                    if (food.getTitle().equals(object.getTitle())) {
                        isAlreadyFavorite = true;
                        favoriteIndex = i;
                        break;
                    }
                }

                if (isAlreadyFavorite) {
                    currentUser.getFavoriteFoods().remove(favoriteIndex);
                    object.setIsFavorite(false);
                } else {
                    currentUser.getFavoriteFoods().add(object);
                    object.setIsFavorite(true);
                }

                isFavorite = !isFavorite;
                updateFavoriteButtonState();
            }
        });
    }

    private void getBundle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        ivDetailPic.setImageResource(drawableResourceId);

        txtDetailTitle.setText(object.getTitle());
        txtDetailPrice.setText(object.getFee() + "€");
        txtDescription.setText(object.getDescription());
        txtAmount.setText(String.valueOf(orderAmount));

        ivPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderAmount = orderAmount + 1;
                txtAmount.setText(String.valueOf(orderAmount));
            }
        });

        ivMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderAmount > 1) {
                    orderAmount = orderAmount - 1;
                }
                txtAmount.setText(String.valueOf(orderAmount));
            }
        });

        // Check if the item is already in the user's favorites
        User currentUser = UserAuthenticator.getInstance().getAuthenticatedUser();
        if (currentUser.getFavoriteFoods() != null) {
            for (FoodDomain food : currentUser.getFavoriteFoods()) {
                if (food.getTitle().equals(object.getTitle())) {
                    isFavorite = true;
                    break;
                }
            }
        }
        updateFavoriteButtonState();
    }

    private void updateFavoriteButtonState() {
        if (isFavorite) {
            btnAddToFavorites.setImageResource(R.drawable.ic_star_favorite);
        } else {
            btnAddToFavorites.setImageResource(R.drawable.ic_star_normal);
        }
    }

    private void initView() {
        btnAddToCart = findViewById(R.id.btnAddToCart);
        txtDetailTitle = findViewById(R.id.txtDetailTitle);
        txtDetailPrice = findViewById(R.id.txtDetailPrice);
        txtDescription = findViewById(R.id.txtDescription);
        txtAmount = findViewById(R.id.txtAmount);
        ivMinusButton = findViewById(R.id.ivMinusButton);
        ivPlusButton = findViewById(R.id.ivPlusButton);
        ivDetailPic = findViewById(R.id.ivDetailPic);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);
    }
}