package com.reddit.controller;

import com.reddit.model.User;
import com.reddit.service.UserService;

import java.util.Scanner;

public class UserController {
    private final UserService userService;
    private final Scanner scanner;

    public UserController(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void updateUserProfile(User user) {
        System.out.print("Enter new email (or leave blank to keep current): ");
        String newEmail = scanner.nextLine();

        System.out.print("Enter new password (or leave blank to keep current): ");
        String newPassword = scanner.nextLine();

        if (!newEmail.isEmpty()) {
            user.setEmail(newEmail);
        }

        if (!newPassword.isEmpty()) {
            user.setPassword(PasswordHasher.hashPassword(newPassword));
        }

        userService.updateUser(user);
        System.out.println("User profile updated successfully!");
    }

    public void viewUserProfile(User user) {
        System.out.println("Email: " + user.getEmail());
        System.out.println("