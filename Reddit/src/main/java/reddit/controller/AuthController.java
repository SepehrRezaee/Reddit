package com.reddit.controller;

import com.reddit.model.User;
import com.reddit.service.AuthService;
import com.reddit.util.PasswordHasher;

import java.util.Scanner;
import java.util.regex.Pattern;

public class AuthController {
    private final AuthService authService;
    private final Scanner scanner;

    public AuthController(AuthService authService) {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
    }

    public void registerUser() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format!");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(email, PasswordHasher.hashPassword(password));
        authService.registerUser(user);
        System.out.println("User registered successfully!");
    }

    public void loginUser() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = authService.authenticateUser(email, password);
        if (user != null) {
            System.out.println("Login successful!");
            // Set the logged-in user in the application context
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}