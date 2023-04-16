package com.example.foodorderingapp.General;

public class UserManager {
    private static UserManager instance;
    private User currentUser;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void updateCurrentUser(String username, String email, String password, String address) {
        if (this.currentUser != null) {
            this.currentUser.setUsername(username);
            this.currentUser.setEmail(email);
            this.currentUser.setPassword(password);
            this.currentUser.setAddress(address);
        }
    }
}
