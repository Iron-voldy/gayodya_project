package com.fooddelivery.util;

import com.fooddelivery.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserFileHandler {
    private static final String FILE_PATH = "users.txt";

    public static void saveUser(User user) {
        String data = user.toFileString() + "\n";
        FileHandler.appendToFile(FILE_PATH, data);
    }

    public static List<User> getAllUsers() {
        List<String> lines = FileHandler.readFromFile(FILE_PATH);
        List<User> users = new ArrayList<>();

        for (String line : lines) {
            User user = User.fromFileString(line);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }

    // Other user-specific file operations...
}