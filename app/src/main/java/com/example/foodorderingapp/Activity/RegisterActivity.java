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
                /*
                Database db = new Database(getApplicationContext(), "foodOrderingApp", null, 1);
                // TODO CLEAN THIS TO A SEPARATE CLASS
                // TODO CHECK FOR DUPLICATES!
                // TODO CHECK IF USERNAME CAN BE USED AS A FILE NAME
                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || confirm.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    if (password.compareTo(confirm) == 0) {
                        if (isPasswordValid(password)) {
                            if (isEmailValid(email)) {
                                db.register(username, email, password);
                                Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter a valid email address", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must be at least 8 characters long and include a minimum of one letter, one digit.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password and Confirm password didn't match", Toast.LENGTH_LONG).show();
                    }
                }

                 */
            }
        });
    }

    public static boolean isPasswordValid(String password) {
        int f1 = 0, f2 = 0;
        if (password.length() < 8) { // Checks if the password has at least 8 characters in length.
            return false;
        } else {
            for (int i = 0; i < password.length(); i++ ) { // Checks that the password contains at least one letter (uppercase or lowercase).
                if (Character.isLetter(password.charAt(i))) {
                    f1 = 1;
                }
            }
            for (int j = 0; j < password.length(); j++ ) { // Checks that the password contains at least one digit (0-9).
                if (Character.isDigit(password.charAt(j))) {
                    f2 = 1;
                }
            }
            if (f1 == 1 && f2 == 1)
                return true;
            return false;
        }
    }

    // This method finds the positions of the first '@' character and the last '.' character.
    // It checks that '@' is present and there is at least one character before it,
    // and that '.' is present after '@' with at least one character between them,
    // and at least one character after '.'.
    public static boolean isEmailValid (String email) {
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        if (atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < email.length() - 1) {
            return true;
        }
        return false;
    }

}