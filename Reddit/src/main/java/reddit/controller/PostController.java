package com.reddit.controller;

import com.reddit.model.Post;
import com.reddit.model.Subreddit;
import com.reddit.model.User;
import com.reddit.service.PostService;

import java.util.List;
import java.util.Scanner;

public class PostController {
    private final PostService postService;
    private final Scanner scanner;

    public PostController(PostService postService) {
        this.postService = postService;
        this.scanner = new Scanner(System.in);
    }

    public void createPost(User user, Subreddit subreddit) {
        System.out.print("Enter post title: ");
        String title = scanner.nextLine();

        System.out.print("Enter post body: ");
        String body = scanner.nextLine();

        // Handle tags/flairs if needed

        Post post = new Post(title, body, user, subreddit);
        postService.createPost(post);
        System.out.println("Post created successfully!");
    }

    public void viewPost(Post post) {
        System.out.println("Title: " + post.getTitle());
        System.out.println("Body: " + post.getBody());
        System.out.println("Author: " + post.getAuthor().getEmail());
        System.out.println("Subreddit: " + post.getSubreddit().getName());
        System.out.println("Karma: " + post.getKarma());

        // Display comments
        List<Comment> comments = postService.getCommentsForPost(post);
        for (Comment comment : comments) {
            System.out.println("Comment by " + comment.getAuthor().getEmail() + ": " + comment.getBody());
        }
    }

    public void viewPostsForSubreddit(Subreddit subreddit) {
        List<Post> posts = postService.getPostsForSubreddit(subreddit);
        for (Post post : posts) {
            System.out.println("Title: " + post.getTitle());
            System.out.println("Author: " + post.getAuthor().getEmail());
            System.out.println("Karma: " + post.getKarma());
            System.out.println();
        }
    }

    public void upvotePost(User user, Post post) {
        postService.upvotePost(user, post);
        System.out.println("Post upvoted!");
    }

    public void downvotePost(User user, Post post) {
        postService.downvotePost(user, post);
        System.out.println("Post downvoted!");
    }

    // Add other post-related methods as needed
}