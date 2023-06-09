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
import com.example.foodorderingapp.Manager.CartManager;
import com.example.foodorderingapp.Manager.FavoritesManager;
import com.example.foodorderingapp.General.User;
import com.example.foodorderingapp.Helpers.UserAuthenticator;
import com.example.foodorderingapp.Manager.UserManager;
import com.example.foodorderingapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etLoginEmail, etLoginPassword;
    private TextView signUpButton;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginEmail = findViewById(R.id.etUsername);
        etLoginPassword = findViewById(R.id.etEmail);
        signUpButton = findViewById(R.id.txtSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        ImageView backgroundImageView = findViewById(R.id.LoginBackgroundImageView);
        backgroundImageView.setImageResource(R.drawable.wallpaper2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etLoginEmail.getText().toString();
                String password = etLoginPassword.getText().toString();
                if (email.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                    return;
                }
                User user = login(email, password);
                if (user != null) {
                    CartManager.getInstance().initializeCart(LoginActivity.this, user); // Loads the users cart from a serialized file

                    UserAuthenticator userAuthenticator = UserAuthenticator.getInstance();
                    userAuthenticator.setAuthenticatedUser(user); // Sets the current user

                    FavoritesManager favoritesManager = FavoritesManager.getInstance();
                    user.setFavoriteFoods(favoritesManager.loadFavorites(LoginActivity.this, user)); // Load the users favorites from a serialized file

                    Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                    startActivity(intent);

                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Could not find an account with this email and password combination", Toast.LENGTH_LONG).show();
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    // Checks if the email and password matches to user that has been serialized in a file that maintains registered users
    private User login(String email, String password) {
        UserManager userManager = UserManager.getInstance();
        userManager.initializeUsers(LoginActivity.this);
        User user = userManager.getUser(email, password);
        return user;
    }

}