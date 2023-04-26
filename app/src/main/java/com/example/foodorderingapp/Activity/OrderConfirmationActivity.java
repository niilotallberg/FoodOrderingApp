package com.example.foodorderingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.foodorderingapp.General.User;
import com.example.foodorderingapp.Helpers.UserAuthenticator;
import com.example.foodorderingapp.Manager.CartManager;
import com.example.foodorderingapp.R;

public class OrderConfirmationActivity extends AppCompatActivity {
    private Button btnAddCard, btnConfirm;
    private TextView twCode;
    private RadioGroup radioGroupDeliveryMethod;
    private RadioButton radioBtnDelivery, radioBtnPickup;
    private String name, code, expiring, safety;
    private boolean cardAdded = false;
    private EditText etDeliveryAddress;
    private ConstraintLayout deliveryAddressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        btnAddCard = findViewById(R.id.btnAddCard);
        btnConfirm = findViewById(R.id.btnConfirm);
        twCode = findViewById(R.id.twCode);
        radioGroupDeliveryMethod = findViewById(R.id.radioGroupDeliveryMethod);
        radioBtnDelivery = findViewById(R.id.radioBtnDelivery);
        radioBtnPickup = findViewById(R.id.radioBtnPickup);
        etDeliveryAddress = findViewById(R.id.etDeliveryAddress);
        deliveryAddressLayout = findViewById(R.id.deliveryAddressLayout);

        deliveryAddressLayout.setVisibility(radioBtnDelivery.isChecked() ? View.VISIBLE : View.GONE);

        radioGroupDeliveryMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioBtnDelivery) {
                    deliveryAddressLayout.setVisibility(View.VISIBLE);
                } else {
                    deliveryAddressLayout.setVisibility(View.GONE);
                }
            }
        });

        User currentUser = UserAuthenticator.getInstance().getAuthenticatedUser();
        if (currentUser != null && currentUser.getAddress() != null) {
            etDeliveryAddress.setText(currentUser.getAddress());
        }

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
                if (validatePaymentAndDelivery()) {
                    CartManager cartManager = CartManager.getInstance();
                    cartManager.clearCart(OrderConfirmationActivity.this);

                    Intent intent = new Intent(OrderConfirmationActivity.this, WaitingScreenActivity.class);
                    if (radioBtnDelivery.isChecked()) {
                        intent.putExtra("orderStatus", "Your order is on the way");
                    } else if (radioBtnPickup.isChecked()) {
                        intent.putExtra("orderStatus", "You can pickup your order in");
                    }
                    intent.putExtra("fromOrderConfirmation", true);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
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

    private boolean validatePaymentAndDelivery() {
        RadioButton radioButtonMobilePay = findViewById(R.id.radioButton2);
        RadioButton radioButtonPayPal = findViewById(R.id.radioButton);

        if (!cardAdded && !radioButtonMobilePay.isChecked() && !radioButtonPayPal.isChecked()) {
            Toast.makeText(this, "Please input your card or choose a different payment method", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radioGroupDeliveryMethod.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a delivery method", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}