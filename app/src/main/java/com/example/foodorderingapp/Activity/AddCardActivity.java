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
                if (validateCardNumber() && validateExpiringDate() && validateSafetyNumber()) {
                    saveCard();
                }
            }
        });
    }

    private boolean validateCardNumber() {
        String cardNumber = etCardNumber.getText().toString();
        if (cardNumber.length() > 12) {
            etCardNumber.setError("Card number must be no more than 12 digits");
            return false;
        } else {
            return true;
        }
    }

    private boolean validateExpiringDate() {
        String expiringDate = etExpiringDate.getText().toString();
        if (!expiringDate.matches("\\d{2}/\\d{2}")) {
            etExpiringDate.setError("Expiring date must be in the format MM/YY");
            return false;
        } else {
            return true;
        }
    }

    private boolean validateSafetyNumber() {
        String safetyNumber = etSafetyNumber.getText().toString();
        if (safetyNumber.length() != 3) {
            etSafetyNumber.setError("Safety number must be exactly 3 digits");
            return false;
        } else {
            return true;
        }
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

