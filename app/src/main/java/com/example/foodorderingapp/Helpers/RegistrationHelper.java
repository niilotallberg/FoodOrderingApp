// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.Helpers;

import com.example.foodorderingapp.General.User;
import com.example.foodorderingapp.General.UserManager;

import java.util.regex.Pattern;

public class RegistrationHelper {

    public static boolean isPasswordValid(String password) {
        int f1 = 0, f2 = 0;
        if (password.length() < 8) { // Checks if the password has at least 8 characters in length.
            return false;
        } else {
            for (int i = 0; i < password.length(); i++ ) { // Checks that the password contains at least one letter (uppercase or lowercase).
                if (Character.isLetter(password.charAt(i))) {
                    f1 = 1;
                }
            }
            for (int j = 0; j < password.length(); j++ ) { // Checks that the password contains at least one digit (0-9).
                if (Character.isDigit(password.charAt(j))) {
                    f2 = 1;
                }
            }
            if (f1 == 1 && f2 == 1)
                return true;
            return false;
        }
    }

    // This method finds the positions of the first '@' character and the last '.' character.
    // It checks that '@' is present and there is at least one character before it,
    // and that '.' is present after '@' with at least one character between them,
    // and at least one character after '.'.
    public static boolean isEmailValid (String email) {
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        if (atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < email.length() - 1) {
            return true;
        }
        return false;
    }

    // Checks that the username includes only characters and numbers so it can be used as a name for a file
    public static boolean isUsernameValid(String username) {
        return Pattern.compile("^[a-zA-Z0-9]+$").matcher(username).matches();
    }

    public static String checkRegistrationInformation(String username, String email, String password, String confirmPassword, UserManager userManager) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return "Please fill all the fields";
        } else if (!isUsernameValid(username)) {
            return "Invalid username. Only letters and numbers are allowed.";
        } else if (userManager.isUserExists(username)) {
            return "Username already exists. Please choose a different one.";
        } else if (!isEmailValid(email)) {
            return "Invalid email address. Please enter a valid email.";
        } else if (userManager.isEmailExists(email)) {
            return "Email address already exists. Please choose a different one.";
        } else if (!isPasswordValid(password)) {
            return "Password must be at least 8 characters long and include a minimum of one letter, one digit.";
        } else if (!password.equals(confirmPassword)) {
            return "Password and Confirm password didn't match";
        } else {
            return null;
        }
    }
}
