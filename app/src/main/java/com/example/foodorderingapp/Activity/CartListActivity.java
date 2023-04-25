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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.foodorderingapp.Adapter.CartListAdapter;
import com.example.foodorderingapp.General.FoodDomain;
import com.example.foodorderingapp.General.User;
import com.example.foodorderingapp.Manager.UserManager;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.Manager.CartManager;
import com.example.foodorderingapp.Interface.ChangeNumberItemsListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.HashMap;
import java.util.Map;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private TextView txtTotalPricePrice, txtTaxPrice, txtDeliveryFeePrice, txtTotalItemsPrice, txtEmptyCart, btnCheckout;
    private double tax;
    private ScrollView svCart;
    private CartManager cartManager;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        initView();
        cartManager = CartManager.getInstance();

        userManager = UserManager.getInstance();
        User currentUser = userManager.getCurrentUser();
        cartManager.initializeCart(getApplicationContext(), currentUser);

        btnCheckout = findViewById(R.id.btnCheckout);

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, OrderConfirmationActivity.class));
            }
        });

        initList();
        CalculateCart();
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
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, HomePageActivity.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, ProfileSettingsActivity.class));
            }
        });

        favoritesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, FavoritesActivity.class));
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, FaqActivity.class));
            }
        });
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.rvTotalItems);
        txtTotalPricePrice = findViewById(R.id.txtTotalPricePrice);
        txtTaxPrice = findViewById(R.id.txtTaxPrice);
        txtDeliveryFeePrice = findViewById(R.id.txtDeliveryFeePrice);
        txtTotalItemsPrice = findViewById(R.id.txtTotalItemsPrice);
        txtEmptyCart = findViewById(R.id.txtEmptyCart);
        svCart = findViewById(R.id.svCart);
        recyclerViewList = findViewById(R.id.rvTotalItems);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        adapter = new CartListAdapter(cartManager, this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);

        if (cartManager.getCartItems().isEmpty()) {
            txtEmptyCart.setVisibility(View.VISIBLE);
            svCart.setVisibility(View.GONE);
        } else {
            txtEmptyCart.setVisibility(View.GONE);
            svCart.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart() {
        double percentTax = 0.14;
        double delivery = 5;
        double itemTotal = 0;

        HashMap<FoodDomain, Integer> cartItems = cartManager.getCartItems();
        for (Map.Entry<FoodDomain, Integer> entry : cartItems.entrySet()) {
            itemTotal += entry.getKey().getFee() * entry.getValue();
        }

        if (cartItems.isEmpty()) {
            delivery = 0;
        }

        tax = Math.round((itemTotal * percentTax) * 100) / 100;
        double total = Math.round((itemTotal + tax + delivery) * 100) / 100.0;



        txtTaxPrice.setText(String.format("%.2f€", tax));
        txtDeliveryFeePrice.setText(String.format("%.2f€", delivery));
        txtTotalItemsPrice.setText(String.format("%.2f€", itemTotal));
        txtTotalPricePrice.setText(String.format("%.2f€", total));
    }
}
