// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.General;

import com.example.foodorderingapp.R;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    public static final int DEFAULT_PROFILE_PICTURE_ID = R.drawable.baseline_profile_picture;
    private static final long serialVersionUID = 639424123;
    private String username;
    private String email;
    private String password;
    private String address;
    private int profilePicture;
    private ArrayList<FoodDomain> favoriteFoods;

    public User(String username, String email, String password, String address, int profilePicture) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.profilePicture = profilePicture;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public ArrayList<FoodDomain> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(ArrayList<FoodDomain> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }
}
