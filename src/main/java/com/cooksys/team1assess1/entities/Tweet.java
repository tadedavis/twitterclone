package com.cooksys.team1assess1.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class Tweet {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User author;

    private Timestamp posted;

    private boolean deleted = false;

    private String content;


    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet inReplyTo;

    @OneToMany(mappedBy = "inReplyTo")
    private List<Tweet> RepliedBy;

    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet repostOf;

    @OneToMany(mappedBy = "repostOf")
    private List<Tweet> RepostTweets;


    @ManyToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> likes;

    @ManyToMany
    @JoinTable(
            name = "mentions",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> mentions;

    @OneToMany(mappedBy = "hashtag")
    private List<Hashtag> hashtags;

}
