package com.example.foodorderingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodorderingapp.General.User;
import com.example.foodorderingapp.General.UserAuthenticator;
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
        User currentUser = UserAuthenticator.getInstance().getAuthenticatedUser();
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
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String address = etAddress.getText().toString();

                if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty() && !address.isEmpty()) {
                    UserManager userManager = UserManager.getInstance(getApplicationContext());
                    currentUser.setUsername(username);
                    currentUser.setEmail(email);
                    currentUser.setPassword(password);
                    currentUser.setAddress(address);
                    // TODO PROFILE PICTURE
                    userManager.updateUser(currentUser);
                    Toast.makeText(ProfileSettingsActivity.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileSettingsActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}