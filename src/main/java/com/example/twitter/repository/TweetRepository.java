package com.example.twitter.repository;

import com.example.twitter.model.Tweet;
import com.example.twitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.List;


@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {

    List<Tweet> findByUserInAndReplyToIsNotNull(Collection<User> users);
}




