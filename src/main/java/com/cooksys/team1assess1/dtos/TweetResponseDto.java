package com.cooksys.team1assess1.dtos;

import com.cooksys.team1assess1.entities.Hashtag;
import com.cooksys.team1assess1.entities.Tweet;
import com.cooksys.team1assess1.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Data
public class TweetResponseDto {
    private User author;

    private Timestamp posted;

    private boolean deleted;

    private String content;

    private Tweet inReplyTo;

    private Tweet repostOf;

    private List<User> likes;

    private List<User> mentions;

    private List<Hashtag> hashtags;
}
