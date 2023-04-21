package com.example.foodorderingapp.General;

public class UserAuthenticator {
    private static UserAuthenticator instance;
    private User authenticatedUser;

    private UserAuthenticator() {
    }

    public static UserAuthenticator getInstance() {
        if (instance == null) {
            instance = new UserAuthenticator();
        }
        return instance;
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }
}
