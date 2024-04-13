package com.reddit.service;

import com.reddit.model.Comment;
import com.reddit.model.Post;
import com.reddit.model.Subreddit;
import com.reddit.model.User;
import com.reddit.repository.CommentRepository;
import com.reddit.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PostService {
    public void createPost(Post post) {
        PostRepository.savePost(post);
        post.getSubreddit().addPost(post);
    }

    public List<Comment> getCommentsForPost(Post post) {
        return post.getComments();
    }

    public List<Post> getPostsForSubreddit(Subreddit subreddit) {
        return subreddit.getPosts().stream().collect(Collectors.toList());
    }

    public void upvotePost(User user, Post post) {
        post.upvote();
        CommentRepository.saveComment(new Comment("Upvoted post", user, post));
    }

    public void downvotePost(User user, Post post) {
        post.downvote();
        CommentRepository.saveComment(new Comment("Downvoted post", user, post));
    }
}