// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.foodorderingapp.General.User;
import com.example.foodorderingapp.Manager.UserManager;
import com.example.foodorderingapp.Helpers.RegistrationHelper;
import com.example.foodorderingapp.R;

public class RegisterActivity extends AppCompatActivity {
    private TextView txtAlreadyHaveAnAccount;
    private EditText etRegisterUsername, etRegisterEmail, etRegisterPassword, etConfirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageView backgroundImageView = findViewById(R.id.RegisterBackgroundImageView);
        backgroundImageView.setImageResource(R.drawable.wallpaper2);

        txtAlreadyHaveAnAccount = findViewById(R.id.txtAlreadyHaveAnAccout);
        etRegisterUsername = findViewById(R.id.etUsername);
        etRegisterEmail = findViewById(R.id.etEmail);
        etRegisterPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);


        txtAlreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etRegisterUsername.getText().toString();
                String email = etRegisterEmail.getText().toString();
                String password = etRegisterPassword.getText().toString();
                String confirm = etConfirmPassword.getText().toString();

                UserManager userManager = UserManager.getInstance();
                userManager.initializeUsers(RegisterActivity.this); // Load the previously registered users from the serialized file

                String errorMessage = RegistrationHelper.checkRegistrationInformation(username, email, password, confirm, userManager); // Checks if there is any errors with the registration information

                if (errorMessage != null) {
                    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                } else {
                    User newUser = new User(username, email, password, "", User.DEFAULT_PROFILE_PICTURE_ID);
                    userManager.addUser(RegisterActivity.this, newUser); // Registration information was ok -> Add the new user to the serialized file

                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}