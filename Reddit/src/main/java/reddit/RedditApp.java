package com.reddit;

import com.reddit.controller.AuthController;
import com.reddit.controller.PostController;
import com.reddit.controller.SubredditController;
import com.reddit.controller.UserController;
import com.reddit.model.Post;
import com.reddit.model.Subreddit;
import com.reddit.model.User;
import com.reddit.repository.CommentRepository;
import com.reddit.repository.PostRepository;
import com.reddit.repository.SubredditRepository;
import com.reddit.repository.UserRepository;
import com.reddit.service.AuthService;
import com.reddit.service.PostService;
import com.reddit.service.SubredditService;
import com.reddit.service.UserService;

import java.util.List;
import java.util.Scanner;

public class RedditApp {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    private static AuthController authController;
    private static UserController userController;
    private static SubredditController subredditController;
    private static PostController postController;

    public static void main(String[] args) {
        initializeControllers();
        displayMenu();
    }

    private static void initializeControllers() {
        AuthService authService = new AuthService();
        UserService userService = new UserService();
        SubredditService subredditService = new SubredditService();
        PostService postService = new PostService();

        authController = new AuthController(authService);
        userController = new UserController(userService);
        subredditController = new SubredditController(subredditService);
        postController = new PostController(postService);
    }

    private static void displayMenu() {
        System.out.println("Welcome to Reddit!");
        while (true) {
            if (currentUser == null) {
                displayLoginMenu();
            } else {
                displayUserMenu();
            }
        }
    }

    private static void displayLoginMenu() {
        System.out.println("\n1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                authController.registerUser();
                break;
            case 2:
                currentUser = authController.loginUser();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void displayUserMenu() {
        System.out.println("\n1. View Profile");
        System.out.println("2. Update Profile");
        System.out.println("3. Create Subreddit");
        System.out.println("4. Join Subreddit");
        System.out.println("5. View Joined Subreddits");
        System.out.println("6. Create Post");
        System.out.println("7. View Post");
        System.out.println("0. Logout");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                userController.viewUserProfile(currentUser);
                break;
            case 2:
                userController.updateUserProfile(currentUser);
                break;
            case 3:
                subredditController.createSubreddit(currentUser);
                break;
            case 4:
                joinSubreddit();
                break;
            case 5:
                subredditController.viewJoinedSubreddits(currentUser);
                break;
            case 6:
                createPost();
                break;
            case 7:
                viewPost();
                break;
            case 0:
                currentUser = null;
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void joinSubreddit() {
        System.out.print("Enter subreddit name: ");
        String subredditName = scanner.nextLine();
        Subreddit subreddit = SubredditRepository.getSubredditByName(subredditName);
        if (subreddit != null) {
            subredditController.joinSubreddit(currentUser, subreddit);
        } else {
            System.out.println("Subreddit not found!");
        }
    }

    private static void createPost() {
        System.out.print("Enter subreddit name: ");
        String subredditName = scanner.nextLine();
        Subreddit subreddit = SubredditRepository.getSubredditByName(subredditName);
        if (subreddit != null && subreddit.getMembers().contains(currentUser)) {
            postController.createPost(currentUser, subreddit);
        } else {
            System.out.println("You must join the subreddit first!");
        }
    }

    private static void viewPost() {
        System.out.print("Enter post ID: ");
        String postId = scanner.nextLine();
        Post post = PostRepository.getPostById(postId);
        if (post != null) {
            postController.viewPost(post);
        } else {
            System.out.println("Post not found!");
        }
    }

    private static void searchSubreddits() {
        System.out.print("Enter subreddit name (prefix with 'r/'): ");
        String subredditName = scanner.nextLine();
        if (subredditName.startsWith("r/")) {
            Subreddit subreddit = SubredditRepository.getSubredditByName(subredditName.substring(2));
            if (subreddit != null) {
                System.out.println("Subreddit found: " + subreddit.getName());
                System.out.println("Members: " + subreddit.getMembers().size());
            } else {
                System.out.println("Subreddit not found!");
            }
        } else {
            System.out.println("Invalid subreddit name format!");
        }
    }

    private static void searchUsers() {
        System.out.print("Enter username (prefix with 'u/'): ");
        String username = scanner.nextLine();
        if (username.startsWith("u/")) {
            User user = UserRepository.getUserByEmail(username.substring(2));
            if (user != null) {
                System.out.println("User found: " + user.getEmail());
            } else {
                System.out.println("User not found!");
            }
        } else {
            System.out.println("Invalid username format!");
        }
    }
}