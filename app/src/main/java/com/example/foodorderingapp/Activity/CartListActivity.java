// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s

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

import com.example.foodorderingapp.Adaptor.CartListAdapter;
import com.example.foodorderingapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private TextView txtTotalPricePrice, txtTaxPrice, txtDeliveryFeePrice, txtTotalItemsPrice, txtEmpyCart;
    private double tax;
    // private ManagementCart managementCart;
    private ScrollView svCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        // initialize views
        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.btnCartView);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

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
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.rvTotalItems);
        txtTotalPricePrice = findViewById(R.id.txtTotalPricePrice);
        txtTaxPrice = findViewById(R.id.txtTaxPrice);
        txtDeliveryFeePrice = findViewById(R.id.txtDeliveryFeePrice);
        txtTotalItemsPrice = findViewById(R.id.txtTotalItemsPrice);
        txtEmpyCart = findViewById(R.id.txtEmptyCart);
        svCart = findViewById(R.id.svCart);
        recyclerViewList = findViewById(R.id.rvTotalItems);
    }
    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        // adapter = new CartListAdapter() TODO Toteuta loppuun -> kutsu CalculateCart()

        // recyclerViewList.setAdapter(adapter);

        // if (OSTOSKORI ON TYHJÄ) {
        //    txtEmpyCart.setVisibility(View.VISIBLE);
        //    svCart.setVisibility(View.GONE);
        // } else {
        //    txtEmpyCart.setVisibility(View.GONE);
        //    svCart.setVisibility(View.VISIBLE);
        // }
    }

    private void CalculateCart() {
        double percentTax = 0.14;
        double delivery = 5;

        // tax = Math.round((KOKO SUMMA * percentTax) * 100) / 100 TODO Vaatii ostoskorin toiminnaliisuuden
        // double total = Math.round((KOKO SUMMA + tax + delivery) * 100) / 100
        // double itemTotal = Math.round(KOKO SUMMA * 100) / 100

        // txtTaxPrice.setText(tax + "€");
        // txtDeliveryFeePrice.setText(delivery + "€");
        // txtTotalItemsPrice.setText(itemTotal + "€");
        // txtTotalPricePrice.setText(total + "€");
    }
}