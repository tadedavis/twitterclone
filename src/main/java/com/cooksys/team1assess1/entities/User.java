package com.cooksys.team1assess1.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="user_table")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private Credentials credentials;
    @CreationTimestamp
    private Timestamp joined;
    private boolean deleted = false;
    @Embedded
    private Profile profile;

    @OneToMany(mappedBy = "author")
    private List<Tweet> tweets;

    @ManyToMany(mappedBy = "likes")
    private List<Tweet> likedTweets;

    @ManyToMany(mappedBy = "mentions")
    private List<Tweet> mentionedBy;

    @ManyToMany
    @JoinTable(
            name = "followers_following",
            joinColumns = @JoinColumn(name = "following_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private List<User> following;

    @ManyToMany(mappedBy = "following")
    private List<User> followers;
}
