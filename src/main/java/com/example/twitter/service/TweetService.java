package com.example.twitter.service;

import com.example.twitter.model.Tweet;
import com.example.twitter.model.User;
import com.example.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

    @Autowired
    TweetRepository tweetRepo;

    public List<Tweet> getAllTweets() {
        return tweetRepo.findAll();
    }

    public List<Tweet> getTweetsByUser(User user) {
        return tweetRepo.findByUser(user);
    }







   /* public Tweet createTweet(String content, Integer userId, Integer replyToId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Tweet replyTo = null;
        if (replyToId != null) {
            replyTo = tweetRepository.findById(replyToId).orElse(null);
            if (replyTo == null) {
                throw new IllegalArgumentException("Reply tweet not found");
            }
        }

        Tweet tweet = new Tweet();
        tweet.setContent(content);
        tweet.setUser(user);
        tweet.setReplyTo(replyTo);
        tweet.setDate(new Date());

        return tweetRepository.save(tweet);
    }
    
    */


}

