package com.reddit.model;

public class Comment {
    private String id;
    private String body;
    private User author;
    private Post post;
    private int karma;

    public Comment(String body, User author, Post post) {
        this.body = body;
        this.author = author;
        this.post = post;
        post.addComment(this);
        author.addComment(this);
    }

    // Getters and setters

    public void upvote() {
        karma++;
        author.addComment(this);
    }

    public void downvote() {
        karma--;
        author.addComment(this);
    }

    // Override equals() and hashCode() methods
}