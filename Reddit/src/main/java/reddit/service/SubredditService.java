package com.reddit.service;

import com.reddit.model.Subreddit;
import com.reddit.model.User;
import com.reddit.repository.SubredditRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SubredditService {
    public void createSubreddit(Subreddit subreddit) {
        SubredditRepository.saveSubreddit(subreddit);
    }

    public Subreddit getSubredditByName(String name) {
        return SubredditRepository.getSubredditByName(name);
    }

    public void joinSubreddit(User user, Subreddit subreddit) {
        subreddit.addMember(user);
    }

    public List<Subreddit> getJoinedSubreddits(User user) {
        return user.getJoinedSubreddits().stream().collect(Collectors.toList());
    }

    public void addAdmin(Subreddit subreddit, User user) {
        if (subreddit.getCreator().equals(user) || subreddit.getAdmins().contains(user)) {
            subreddit.addAdmin(user);
        }
    }
}