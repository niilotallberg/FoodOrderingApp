package com.example.foodorderingapp.General;

public class User {
    private String username;
    private String email;
    private String password;
    private String address;
    private String profilePicture;

    public User(String username, String email, String password, String address, String profilePicture) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.profilePicture = profilePicture;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
