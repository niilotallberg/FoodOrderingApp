package com.example.foodorderingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodorderingapp.General.User;
import com.example.foodorderingapp.General.UserManager;
import com.example.foodorderingapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileSettingsActivity extends AppCompatActivity {

    private Button btnLogout;
    private Button btnSaveChanges;
    private TextInputEditText etUsername, etEmail, etPassword, etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        btnLogout = findViewById(R.id.logOutButton);
        btnSaveChanges = findViewById(R.id.saveButton);
        etUsername = findViewById(R.id.editUsername);
        etEmail = findViewById(R.id.editEmail);
        etPassword = findViewById(R.id.editPassword);
        etAddress = findViewById(R.id.editAddress);

        // Set the fields with the known user information
        User currentUser = UserManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            etUsername.setText(currentUser.getUsername());
            etEmail.setText(currentUser.getEmail());
            etPassword.setText(currentUser.getPassword());
            etAddress.setText(currentUser.getAddress());
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileSettingsActivity.this, LoginActivity.class);
                // TODO Ulos-kirjautumis toiminnallisuudet
                startActivity(intent);
            }
        });

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save the changes made by the user
                UserManager.getInstance().updateCurrentUser(
                        etUsername.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString(),
                        etAddress.getText().toString()
                );
                // Save the updated user information to your data source, e.g., database, shared preferences, etc.
            }
        });
    }
}