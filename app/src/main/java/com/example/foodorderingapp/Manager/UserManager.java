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

    private void saveUsers(Context context) { // Saves all the users registered at the moment to a file
        Serializer.serializeObject((ArrayList<User>) users, context, USERS_FILE);
    }

    public void initializeUsers(Context context) {
        users = loadUsers(context);
    }

    public List<User> loadUsers(Context context) { // Loads all the registered users from a file
        List<User> loadedUsers = Serializer.deserializeObject(context, USERS_FILE);
        if (loadedUsers == null) {
            return new ArrayList<>();
        }
        return loadedUsers;
    }

    public void addUser(Context context, User user) { // Takes care of adding a new user (saves the new user to the list in this class and the file in the phones memory)
        users.add(user);
        saveUsers(context);
    }

    public User getUser(String email, String password) { // returns the user object that matches with the given email-password-combination - or null if there was no such user object found
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

    public void updateUser(Context context, User user) { // Takes care of updating a users information (To the list in this class and the file in the phones memory)
        users.removeIf(u -> u.getEmail().equals(user.getEmail()));
        users.add(user);
        saveUsers(context);
    }

    public boolean isUserExists(String username) { // Checks if there is already an account with the given username
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

    public boolean isEmailExists(String email) { // Checks if there is already an account with the given email
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