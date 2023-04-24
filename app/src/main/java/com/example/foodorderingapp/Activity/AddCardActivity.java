// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderingapp.R;

public class AddCardActivity extends AppCompatActivity {

    private EditText etCardNumber;
    private EditText etNameOnCard;

    private EditText etExpiringDate;

    private EditText etSafetyNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        etCardNumber = findViewById(R.id.etCardNumber);
        etNameOnCard = findViewById(R.id.etNameOnCard);
        etExpiringDate = findViewById(R.id.etExpiringDate);
        etSafetyNumber = findViewById(R.id.etSafetyNumber);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCard();
            }
        });
    }

    private void saveCard() {
        String cardNumber = etCardNumber.getText().toString();
        String nameOnCard = etNameOnCard.getText().toString();
        String expiringDate = etExpiringDate.getText().toString();
        String safetyNumber = etSafetyNumber.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("card number", cardNumber);
        intent.putExtra("name on card", nameOnCard);
        intent.putExtra("expiring date", expiringDate);
        intent.putExtra("safety number", safetyNumber);
        setResult(RESULT_OK, intent);
        finish();
    }
}

