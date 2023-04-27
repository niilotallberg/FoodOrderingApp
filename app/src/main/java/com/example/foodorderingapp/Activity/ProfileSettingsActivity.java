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
import android.widget.ImageView;
import android.widget.Toast;
import com.example.foodorderingapp.General.User;
import com.example.foodorderingapp.Helpers.UserAuthenticator;
import com.example.foodorderingapp.Manager.UserManager;
import com.example.foodorderingapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileSettingsActivity extends AppCompatActivity {

    private Button btnLogout;
    private Button btnSaveChanges;
    private TextInputEditText etEmail, etPassword, etAddress;
    private ImageView ivProfileImageOption1, ivProfileImageOption2, ivProfileImageOption3, ivProfileImageOption4;
    private int selectedProfilePictureId;
    private ImageView ivCurrentProfilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        User currentUser = UserAuthenticator.getInstance().getAuthenticatedUser();

        btnLogout = findViewById(R.id.logOutButton);
        btnSaveChanges = findViewById(R.id.saveButton);
        etEmail = findViewById(R.id.etProfileSettingsEmail);
        etPassword = findViewById(R.id.etProfileSettingPassword);
        etAddress = findViewById(R.id.etProfileSettingsAddress);
        ivCurrentProfilePicture = findViewById(R.id.ivCurrentProfilePicture);
        ivCurrentProfilePicture.setImageResource(currentUser.getProfilePicture());
        selectedProfilePictureId = currentUser.getProfilePicture();




        if (currentUser != null) {
            etEmail.setText(currentUser.getEmail());
            etPassword.setText(currentUser.getPassword());
            etAddress.setText(currentUser.getAddress());
        }

        ivProfileImageOption1 = findViewById(R.id.ivProfileImageOption1);
        ivProfileImageOption2 = findViewById(R.id.ivProfileImageOption2);
        ivProfileImageOption3 = findViewById(R.id.ivProfileImageOption3);
        ivProfileImageOption4 = findViewById(R.id.ivProfileImageOption4);

        ivProfileImageOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedProfilePictureId = R.drawable.woman_blond_hair;
                ivCurrentProfilePicture.setImageResource(selectedProfilePictureId);
            }
        });

        ivProfileImageOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedProfilePictureId = R.drawable.woman_brown_hair;
                ivCurrentProfilePicture.setImageResource(selectedProfilePictureId);
            }
        });

        ivProfileImageOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedProfilePictureId = R.drawable.man_blond_hair;
                ivCurrentProfilePicture.setImageResource(selectedProfilePictureId);
            }
        });

        ivProfileImageOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedProfilePictureId = R.drawable.man_brown_hair;
                ivCurrentProfilePicture.setImageResource(selectedProfilePictureId);
            }
        });

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
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String address = etAddress.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    UserManager userManager = UserManager.getInstance();

                    // Check if the email already exists and it's not the current user's email
                    if (userManager.isEmailExists(email) && !email.equals(currentUser.getEmail())) {
                        Toast.makeText(ProfileSettingsActivity.this, "Email already exists. Please use a different one.", Toast.LENGTH_SHORT).show();
                    } else {
                        currentUser.setEmail(email);
                        currentUser.setPassword(password);
                        currentUser.setAddress(address);
                        currentUser.setProfilePicture(selectedProfilePictureId);

                        userManager.updateUser(ProfileSettingsActivity.this, currentUser);
                        Toast.makeText(ProfileSettingsActivity.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ProfileSettingsActivity.this, "Please fill in at least email and password.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}