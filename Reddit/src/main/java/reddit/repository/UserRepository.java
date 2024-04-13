package com.reddit.repository;

import com.reddit.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static Map<String, User> users = new HashMap<>();

    public static void saveUser(User user) {
        users.put(user.getId(), user);
        // Persist user data to a file or database
    }

    public static User getUserById(String id) {
        return users.get(id);
    }

    public static User getUserByEmail(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public static void updateUser(User user) {
        users.put(user.getId(), user);
        // Update user data in the file or database
    }

    // Add other methods as needed
}