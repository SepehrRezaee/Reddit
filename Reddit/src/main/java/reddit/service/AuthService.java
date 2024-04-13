package com.reddit.service;

import com.reddit.model.User;
import com.reddit.repository.UserRepository;
import com.reddit.util.PasswordHasher;

public class AuthService {
    public void registerUser(User user) {
        UserRepository.saveUser(user);
    }

    public User authenticateUser(String email, String password) {
        User user = UserRepository.getUserByEmail(email);
        if (user != null && PasswordHasher.verifyPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}