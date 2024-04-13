package com.reddit.repository;

import com.reddit.model.Comment;

import java.util.HashMap;
import java.util.Map;

public class CommentRepository {
    private static Map<String, Comment> comments = new HashMap<>();

    public static void saveComment(Comment comment) {
        comments.put(comment.getId(), comment);
        // Persist comment data to a file or database
    }

    public static Comment getCommentById(String id) {
        return comments.get(id);
    }

    // Add other methods as needed
}