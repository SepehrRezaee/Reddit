package com.reddit.service;

import com.reddit.model.User;
import com.reddit.repository.UserRepository;

public class UserService {
    public void updateUser(User user) {
        UserRepository.updateUser(user);
    }
}