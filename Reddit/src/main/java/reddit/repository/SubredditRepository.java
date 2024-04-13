package com.reddit.repository;

import com.reddit.model.Subreddit;

import java.util.HashMap;
import java.util.Map;

public class SubredditRepository {
    private static Map<String, Subreddit> subreddits = new HashMap<>();

    public static void saveSubreddit(Subreddit subreddit) {
        subreddits.put(subreddit.getId(), subreddit);
        // Persist subreddit data to a file or database
    }

    public static Subreddit getSubredditById(String id) {
        return subreddits.get(id);
    }

    public static Subreddit getSubredditByName(String name) {
        for (Subreddit subreddit : subreddits.values()) {
            if (subreddit.getName().equals(name)) {
                return subreddit;
            }
        }
        return null;
    }

    // Add other methods as needed
}