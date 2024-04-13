package com.reddit.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String id;
    private String email;
    private String password;
    private Set<Subreddit> joinedSubreddits;
    private Set<Post> posts;
    private Set<Comment> comments;
    private int postKarma;
    private int commentKarma;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.joinedSubreddits = new HashSet<>();
        this.posts = new HashSet<>();
        this.comments = new HashSet<>();
    }

    // Getters and setters

    public void joinSubreddit(Subreddit subreddit) {
        joinedSubreddits.add(subreddit);
        subreddit.addMember(this);
    }

    public void leaveSubreddit(Subreddit subreddit) {
        joinedSubreddits.remove(subreddit);
        subreddit.removeMember(this);
    }

    public void addPost(Post post) {
        posts.add(post);
        postKarma += post.getKarma();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        commentKarma += comment.getKarma();
    }

    public int getTotalKarma() {
        return postKarma + commentKarma;
    }

    // Override equals() and hashCode() methods
}