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
        etExpiringDate = findViewById(R.id.expiring_edit_text);
        etSafetyNumber = findViewById(R.id.safety_edit_text);

        Button tallennaButton = findViewById(R.id.tallenna_button);
        tallennaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tallennaTunnusluku();
            }
        });
    }

    private void tallennaTunnusluku() {
        String tunnuslukuString = etCardNumber.getText().toString();
        String nimiString = etNameOnCard.getText().toString();
        String expiringString = etExpiringDate.getText().toString();
        String safetyString = etSafetyNumber.getText().toString();

        // Tallenna tunnusluku ja nimi tietokantaan tai muuhun tallennuspaikkaan tässä

        Intent intent = new Intent();
        intent.putExtra("tunnusluku", tunnuslukuString);
        intent.putExtra("nimi", nimiString);
        intent.putExtra("expiring", expiringString);
        intent.putExtra("safety", safetyString);
        setResult(RESULT_OK, intent);
        finish();
    }
}

