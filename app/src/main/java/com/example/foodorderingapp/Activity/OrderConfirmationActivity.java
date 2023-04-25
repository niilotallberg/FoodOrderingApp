// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodorderingapp.Manager.CartManager;
import com.example.foodorderingapp.R;
import java.util.ArrayList;

public class OrderConfirmationActivity extends AppCompatActivity {
    private Button btnAddCard, btnConfirm;
    private TextView twCode;
    private CheckBox btnOnTheWay, btnPickup;
    private ArrayList<String> codes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        btnAddCard = findViewById(R.id.btnAddCard);
        btnConfirm = findViewById(R.id.btnConfirm);
        twCode = findViewById(R.id.twCode);
        btnOnTheWay = findViewById(R.id.btnOnTheWay);
        btnPickup = findViewById(R.id.btnPickup);
        codes = new ArrayList<>();

        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirmationActivity.this, AddCardActivity.class);
                 startActivityForResult(intent,1);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartManager cartManager = CartManager.getInstance();
                cartManager.clearCart(OrderConfirmationActivity.this);

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
            String name = data.getStringExtra("name on card");
            String code = data.getStringExtra("card number");
            String expiring = data.getStringExtra("expiring date");
            String safety = data.getStringExtra("safety number");

            codes.add(name + ": " + code + ": " + expiring + ": " + safety );
            updateTWcodes();
        }
    }

    private void updateTWcodes() {
        StringBuilder builder = new StringBuilder();

        for (String code : codes) {
            builder.append(code).append("\n");
        }

        twCode.setText(builder.toString());
    }

}
