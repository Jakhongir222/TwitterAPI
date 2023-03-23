package com.example.twitter.service;

import com.example.twitter.exceptions.UserDoesNotExistException;
import com.example.twitter.model.Tweet;
import com.example.twitter.model.User;
import com.example.twitter.repository.TweetRepository;
import com.example.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TweetService {

    @Autowired
    TweetRepository tweetRepo;

    @Autowired
    UserRepository userRepository;

    public List<Tweet> getAllTweets() {
        return tweetRepo.findAll();
    }

    public Tweet createTweet(String content, User user) {
        Tweet tweet = new Tweet();
        tweet.setContent(content);
        tweet.setDate(new Date());
        tweet.setUser(user);
        return tweetRepo.save(tweet);
    }

    public void deleteTweet(Integer tweetId) {
        tweetRepo.deleteById(tweetId);
    }

    public List<Tweet> getTweetsByUser(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserDoesNotExistException();
        }
        List<User> users = new ArrayList<>();
        users.add(user);
        users.addAll(user.getFollowing());
        return tweetRepo.findByUserInAndReplyToIsNotNull(users);
    }
}

