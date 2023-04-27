// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.Manager;

import android.content.Context;
import com.example.foodorderingapp.Data.Serializer;
import com.example.foodorderingapp.General.FoodDomain;
import com.example.foodorderingapp.General.User;
import java.util.ArrayList;

public class FavoritesManager {
    private static FavoritesManager instance;
    private FavoritesManager() {}

    public static FavoritesManager getInstance() {
        if (instance == null) {
            instance = new FavoritesManager();
        }
        return instance;
    }

    private String getFavoritesFilename(String username) {
        return username + "_favorites.ser";
    }

    public void saveFavorites(Context context, User user, ArrayList<FoodDomain> favorites) {
        String filename = getFavoritesFilename(user.getUsername());
        Serializer.serializeObject(favorites, context, filename);
    }

    public ArrayList<FoodDomain> loadFavorites(Context context, User user) {
        String filename = getFavoritesFilename(user.getUsername());
        ArrayList<FoodDomain> favorites = Serializer.deserializeObject(context, filename);
        if (favorites == null) {
            return new ArrayList<>();
        }
        return favorites;
    }
}