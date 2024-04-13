package com.reddit.model;

import java.util.HashSet;
import java.util.Set;

public class Subreddit {
    private String id;
    private String name;
    private User creator;
    private Set<User> members;
    private Set<User> admins;
    private Set<Post> posts;

    public Subreddit(String name, User creator) {
        this.name = name;
        this.creator = creator;
        this.members = new HashSet<>();
        this.admins = new HashSet<>();
        this.posts = new HashSet<>();
        addMember(creator);
        addAdmin(creator);
    }

    // Getters and setters

    public void addMember(User member) {
        members.add(member);
        member.joinSubreddit(this);
    }

    public void removeMember(User member) {
        members.remove(member);
        member.leaveSubreddit(this);
    }

    public void addAdmin(User admin) {
        admins.add(admin);
    }

    public void removeAdmin(User admin) {
        admins.remove(admin);
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    // Override equals() and hashCode() methods
}