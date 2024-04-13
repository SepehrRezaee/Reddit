package com.reddit.controller;

import com.reddit.model.Subreddit;
import com.reddit.model.User;
import com.reddit.service.SubredditService;

import java.util.List;
import java.util.Scanner;

public class SubredditController {
    private final SubredditService subredditService;
    private final Scanner scanner;

    public SubredditController(SubredditService subredditService) {
        this.subredditService = subredditService;
        this.scanner = new Scanner(System.in);
    }

    public void createSubreddit(User user) {
        System.out.print("Enter subreddit name: ");
        String name = scanner.nextLine();

        Subreddit subreddit = new Subreddit(name, user);
        subredditService.createSubreddit(subreddit);
        System.out.println("Subreddit created successfully!");
    }

    public void joinSubreddit(User user) {
        System.out.print("Enter subreddit name: ");
        String name = scanner.nextLine();

        Subreddit subreddit = subredditService.getSubredditByName(name);
        if (subreddit != null) {
            subredditService.joinSubreddit(user, subreddit);
            System.out.println("Joined subreddit successfully!");
        } else {
            System.out.println("Subreddit not found!");
        }
    }

    public void viewJoinedSubreddits(User user) {
        List<Subreddit> subreddits = subredditService.getJoinedSubreddits(user);
        for (Subreddit subreddit : subreddits) {
            System.out.println("Name: " + subreddit.getName());
            System.out.println("Members: " + subreddit.getMembers().size());
            System.out.println();
        }
    }

    public void addAdmin(Subreddit subreddit, User user) {
        subredditService.addAdmin(subreddit, user);
        System.out.println("Admin added successfully!");
    }

    // Add other subreddit-related methods as needed
}