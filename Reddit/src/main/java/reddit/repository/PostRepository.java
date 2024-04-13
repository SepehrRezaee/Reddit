package com.reddit.repository;

import com.reddit.model.Post;

import java.util.HashMap;
import java.util.Map;

public class PostRepository {
    private static Map<String, Post> posts = new HashMap<>();

    public static void savePost(Post post) {
        posts.put(post.getId(), post);
        // Persist post data to a file or database
    }

    public static Post getPostById(String id) {
        return posts.get(id);
    }

    // Add other methods as needed
}