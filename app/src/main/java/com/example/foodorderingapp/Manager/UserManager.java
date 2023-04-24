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
    private Context context;

    private UserManager(Context context) {
        this.context = context;
        users = loadUsers();
        currentUser = null;
    }

    public static UserManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserManager(context);
        }
        return instance;
    }

    private List<User> loadUsers() {
        List<User> loadedUsers = Serializer.deserializeObject(context, USERS_FILE);
        if (loadedUsers == null) {
            return new ArrayList<>();
        }
        return loadedUsers;
    }

    private void saveUsers() {
        Serializer.serializeObject((ArrayList<User>) users, context, USERS_FILE);
    }

    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    public Optional<User> getUser(String email, String password) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst();
    }

    public void updateUser(User user) {
        users.removeIf(u -> u.getEmail().equals(user.getEmail()));
        users.add(user);
        saveUsers();
    }

    public boolean isUserExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}