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

import com.example.foodorderingapp.R;

import java.util.ArrayList;

public class OrderConfirmationActivity extends AppCompatActivity {
    private Button addCardButton,btnConfirm1;
    private TextView tunnusluvutTextView ;

    private CheckBox onTheWayRadioButton, pickupRadioButton;


    private ArrayList<String> tunnusluvut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        addCardButton = findViewById(R.id.add_card_button);
        btnConfirm1 = findViewById(R.id.btnConfirm);
        tunnusluvutTextView = findViewById(R.id.tunnuslukutxt);
        onTheWayRadioButton = findViewById(R.id.delivery_button);
        pickupRadioButton = findViewById(R.id.pickup_button);
        tunnusluvut = new ArrayList<>();

        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirmationActivity.this, AddCardActivity.class);
                startActivityForResult(intent,1);
            }
        });

        btnConfirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirmationActivity.this, WaitingScreenActivity.class);
                if (onTheWayRadioButton.isChecked()) {
                    intent.putExtra("orderStatus", "Your order is on the way");
                } else if (pickupRadioButton.isChecked()) {
                    intent.putExtra("orderStatus", "You can pickup your order in");
                }
                intent.putExtra("fromOrderConfirmation", true); // Add extra to indicate that the user is coming from OrderConfirmation
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String nimi = data.getStringExtra("nimi");
            String tunnusluku = data.getStringExtra("tunnusluku");
            String expiring = data.getStringExtra("expiring");
            String safety = data.getStringExtra("safety");

            tunnusluvut.add(nimi + ": " + tunnusluku + ": " + expiring + ": " + safety );
            updateTunnuslukuvutTextView();
        }
    }

    private void updateTunnuslukuvutTextView() {
        StringBuilder builder = new StringBuilder();

        for (String tunnusluku : tunnusluvut) {
            builder.append(tunnusluku).append("\n");
        }

        tunnusluvutTextView.setText(builder.toString());
    }

}
