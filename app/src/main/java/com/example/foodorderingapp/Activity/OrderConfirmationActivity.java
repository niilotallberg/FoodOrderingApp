package com.example.foodorderingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderingapp.Manager.CartManager;
import com.example.foodorderingapp.R;

public class OrderConfirmationActivity extends AppCompatActivity {
    private Button btnAddCard, btnConfirm;
    private TextView twCode;
    private CheckBox btnOnTheWay, btnPickup;
    private String name, code, expiring, safety;
    private boolean cardAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        btnAddCard = findViewById(R.id.btnAddCard);
        btnConfirm = findViewById(R.id.btnConfirm);
        twCode = findViewById(R.id.twCode);
        btnOnTheWay = findViewById(R.id.btnOnTheWay);
        btnPickup = findViewById(R.id.btnPickup);

        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirmationActivity.this, AddCardActivity.class);
                if (cardAdded) {
                    intent.putExtra("name on card", name);
                    intent.putExtra("card number", code);
                    intent.putExtra("expiring date", expiring);
                    intent.putExtra("safety number", safety);
                }
                startActivityForResult(intent, 1);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartManager cartManager = CartManager.getInstance();
                cartManager.clearCart(OrderConfirmationActivity.this);

                if (!btnOnTheWay.isChecked() && !btnPickup.isChecked()) {
                    // If neither button is selected, an error message is displayed.
                    Toast.makeText(OrderConfirmationActivity.this, "Please select a delivery method", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (btnOnTheWay.isChecked() && btnPickup.isChecked()) {
                    // If both buttons are selected, an error message is displayed.
                    Toast.makeText(OrderConfirmationActivity.this, "Please select only one delivery method", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(OrderConfirmationActivity.this, WaitingScreenActivity.class);
                if (btnOnTheWay.isChecked()) {
                    intent.putExtra("orderStatus", "Your order is on the way");
                } else if (btnPickup.isChecked()) {
                    intent.putExtra("orderStatus", "You can pickup your order in");
                }
                intent.putExtra("fromOrderConfirmation", true);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            name = data.getStringExtra("name on card");
            code = data.getStringExtra("card number");
            expiring = data.getStringExtra("expiring date");
            safety = data.getStringExtra("safety number");
            cardAdded = true;
            updateNewCard();
        }
    }

    private void updateNewCard() {
        twCode.setText(name + ": " + code + ": " + expiring + ": " + safety);
    }
}
