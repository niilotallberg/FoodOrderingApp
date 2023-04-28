// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.foodorderingapp.General.FoodDomain;
import com.example.foodorderingapp.Manager.CartManager;
import com.example.foodorderingapp.Manager.FavoritesManager;
import com.example.foodorderingapp.General.User;
import com.example.foodorderingapp.Helpers.UserAuthenticator;
import com.example.foodorderingapp.R;

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

        btnAddToFavorites.setOnClickListener(new View.OnClickListener() { // Handles the case where user presses the star on the program that indicates if the FoodDomain is one of the users favorites
            @Override
            public void onClick(View view) {
                FavoritesManager favoritesManager = FavoritesManager.getInstance();
                User currentUser = UserAuthenticator.getInstance().getAuthenticatedUser();

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
                favoritesManager.saveFavorites(ShowDetailActivity.this, currentUser, currentUser.getFavoriteFoods());

                isFavorite = !isFavorite;
                updateFavoriteButtonState();
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(txtAmount.getText().toString());

                CartManager cartManager = CartManager.getInstance();
                cartManager.initializeCart(getApplicationContext(), UserAuthenticator.getInstance().getAuthenticatedUser()); // Loads the users current cart
                cartManager.addCartItem(getApplicationContext(), object, quantity); // Add the new item with the right quantity to cart

                String message = "Added " + quantity + " " + object.getTitle() + " to your cart";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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

        ivPlusButton.setOnClickListener(new View.OnClickListener() { // Handles the case where user wants to increase the quantity of a certain item in cart
            @Override
            public void onClick(View view) {
                orderAmount = orderAmount + 1;
                txtAmount.setText(String.valueOf(orderAmount));
            }
        });

        ivMinusButton.setOnClickListener(new View.OnClickListener() { // Handles the case where user wants to decrease the quantity of a certain item in cart
            @Override
            public void onClick(View view) {
                if (orderAmount > 1) {
                    orderAmount = orderAmount - 1;
                }
                txtAmount.setText(String.valueOf(orderAmount));
            }
        });

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

    private void updateFavoriteButtonState() { // Takes care of changing the color of the star in the program that indicates if the foodDomain is a users favorites
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