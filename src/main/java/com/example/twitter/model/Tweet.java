package com.example.twitter.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tweets")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tweet_id")
    private Integer tweetId;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "reply_to")
    private Tweet replyTo;

    public Tweet() {
    }

    public Tweet(String content, Date date, User author, Tweet replyTo) {
        this.content = content;
        this.date = date;
        this.user = user;
        this.replyTo = replyTo;
    }

    public Integer getTweetId() {
        return tweetId;
    }

    public void setTweetId(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tweet getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Tweet replyTo) {
        this.replyTo = replyTo;
    }
}
