// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.Manager;

import android.content.Context;

import com.example.foodorderingapp.Data.Serializer;
import com.example.foodorderingapp.General.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserManager {
    private static final String USERS_FILE = "users.ser";
    private static UserManager instance;
    private List<User> users;
    private User currentUser;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    private void saveUsers(Context context) {
        Serializer.serializeObject((ArrayList<User>) users, context, USERS_FILE);
    }

    public void initializeUsers(Context context) {
        users = loadUsers(context);
    }

    public List<User> loadUsers(Context context) {
        List<User> loadedUsers = Serializer.deserializeObject(context, USERS_FILE);
        if (loadedUsers == null) {
            return new ArrayList<>();
        }
        return loadedUsers;
    }

    public void addUser(Context context, User user) {
        users.add(user);
        saveUsers(context);
    }

    public User getUser(String email, String password) {
        if (users == null) {
            return null;
        } else {
            for (User user : users) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    currentUser = user;
                    return user;
                }
            }
        }
        return null;
    }

    public void updateUser(Context context, User user) {
        users.removeIf(u -> u.getEmail().equals(user.getEmail()));
        users.add(user);
        saveUsers(context);
    }

    public boolean isUserExists(String username) {
        if (users == null) {
            return false;
        }

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    public boolean isEmailExists(String email) {
        if (users == null) {
            return false;
        }
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}