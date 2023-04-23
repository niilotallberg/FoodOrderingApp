// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.General;

import android.content.Context;

import com.example.foodorderingapp.Data.Serializer;
import com.example.foodorderingapp.Domain.FoodDomain;

import java.util.ArrayList;
import java.util.List;

public class FavoritesManager {
    private static FavoritesManager instance;
    private Context context;

    private FavoritesManager(Context context) {
        this.context = context;
    }

    public static FavoritesManager getInstance(Context context) {
        if (instance == null) {
            instance = new FavoritesManager(context);
        }
        return instance;
    }

    private String getFavoritesFilename(String username) {
        return username + "_favorites.ser";
    }

    public void saveFavorites(User user, ArrayList<FoodDomain> favorites) {
        String filename = getFavoritesFilename(user.getUsername());
        Serializer.serializeObject(favorites, context, filename);
    }

    public ArrayList<FoodDomain> loadFavorites(User user) {
        String filename = getFavoritesFilename(user.getUsername());
        ArrayList<FoodDomain> favorites = Serializer.deserializeObject(context, filename);
        if (favorites == null) {
            return new ArrayList<>();
        }
        return favorites;
    }
}