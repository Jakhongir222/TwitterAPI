package com.example.twitter.controller;


import com.example.twitter.model.Tweet;
import com.example.twitter.model.User;
import com.example.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    @Autowired
    TweetService tweetService;

    @GetMapping
    ResponseEntity<List<Tweet>> getAllTweets() {
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.getAllTweets());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Tweet>> getTweetsByUser(@PathVariable("userId") Integer userId) {
        User user = new User(userId);
        List<Tweet> tweets = tweetService.getTweetsByUser(user.getUserId());
        return new ResponseEntity<>(tweets, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tweet> createTweet(@RequestBody TweetRequest request) {
        User user = new User(request.getUserId());
        Tweet tweet = tweetService.createTweet(request.getContent(), user);
        return new ResponseEntity<>(tweet, HttpStatus.CREATED);
    }

    public static class TweetRequest {
        private String content;
        private Integer userId;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }
    }

    @DeleteMapping("/{tweetId}")
    ResponseEntity<Void> deleteTweet(@PathVariable Integer tweetId) {
        tweetService.deleteTweet(tweetId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }





}
