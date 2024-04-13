package com.reddit.model;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private String id;
    private String title;
    private String body;
    private User author;
    private Subreddit subreddit;
    private List<Comment> comments;
    private int karma;

    public Post(String title, String body, User author, Subreddit subreddit) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.subreddit = subreddit;
        this.comments = new ArrayList<>();
    }

    // Getters and setters

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void upvote() {
        karma++;
        author.addPost(this);
    }

    public void downvote() {
        karma--;
        author.addPost(this);
    }

    // Override equals() and hashCode() methods
}