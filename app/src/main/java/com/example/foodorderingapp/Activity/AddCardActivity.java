package com.example.foodorderingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderingapp.R;

public class AddCardActivity extends AppCompatActivity {

    private EditText tunnuslukuEditText;
    private EditText nimiEditText;

    private EditText expiringEditText;

    private EditText safetyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        tunnuslukuEditText = findViewById(R.id.tunnusluku_edit_text);
        nimiEditText = findViewById(R.id.nimi_edit_text);
        expiringEditText = findViewById(R.id.expiring_edit_text);
        safetyEditText = findViewById(R.id.safety_edit_text);

        Button tallennaButton = findViewById(R.id.tallenna_button);
        tallennaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tallennaTunnusluku();
            }
        });
    }

    private void tallennaTunnusluku() {
        String tunnuslukuString = tunnuslukuEditText.getText().toString();
        String nimiString = nimiEditText.getText().toString();
        String expiringString = expiringEditText.getText().toString();
        String safetyString = safetyEditText.getText().toString();

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

