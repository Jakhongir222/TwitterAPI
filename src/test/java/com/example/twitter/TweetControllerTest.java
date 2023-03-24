package com.example.twitter;

import com.example.twitter.model.Tweet;
import com.example.twitter.model.User;
import com.example.twitter.repository.TweetRepository;
import com.example.twitter.repository.UserRepository;
import com.example.twitter.service.TweetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TweetControllerTest {

    @InjectMocks
    private TweetService tweetService;

    @Mock
    private TweetRepository tweetRepo;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTweets() {
        List<Tweet> tweets = new ArrayList<>();
        Tweet tweet1 = new Tweet();
        tweet1.setContent("Tweet 1");
        tweet1.setDate(new Date());
        tweet1.setTweetId(1);
        User user1 = new User();
        user1.setUserId(1);
        user1.setUsername("user1");
        tweet1.setUser(user1);
        tweets.add(tweet1);
        when(tweetRepo.findAll()).thenReturn(tweets);

        List<Tweet> result = tweetService.getAllTweets();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Tweet 1", result.get(0).getContent());
        Assertions.assertEquals(user1, result.get(0).getUser());
    }

    @Test
    public void testCreateTweet() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("user1");
        Tweet tweet = new Tweet();
        tweet.setContent("New Tweet");
        tweet.setDate(new Date());
        tweet.setUser(user);
        when(tweetRepo.save(any(Tweet.class))).thenReturn(tweet);

        Tweet result = tweetService.createTweet("New Tweet", user);
        Assertions.assertEquals("New Tweet", result.getContent());
        Assertions.assertEquals(user, result.getUser());
    }

    @Test
    public void testDeleteTweet() {
        Integer tweetId = 1;
        tweetService.deleteTweet(tweetId);
        Assertions.assertTrue(true);
    }

    @Test
    public void testGetTweetsByUser() {
        User user1 = new User();
        user1.setUserId(1);
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUserId(2);
        user2.setUsername("user2");
        List<Tweet> tweets = new ArrayList<>();
        Tweet tweet1 = new Tweet();
        tweet1.setContent("Tweet 1");
        tweet1.setDate(new Date());
        tweet1.setTweetId(1);
        tweet1.setUser(user1);
        tweets.add(tweet1);
        Tweet tweet2 = new Tweet();
        tweet2.setContent("Tweet 2");
        tweet2.setDate(new Date());
        tweet2.setTweetId(2);
        tweet2.setUser(user2);
        tweet2.setReplyTo(tweet1);
        tweets.add(tweet2);
        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        when(tweetRepo.findByUserInAndReplyToIsNotNull(any(List.class))).thenReturn(tweets);

        List<Tweet> result = tweetService.getTweetsByUser(1);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Tweet 1", result.get(0).getContent());

    }
}


