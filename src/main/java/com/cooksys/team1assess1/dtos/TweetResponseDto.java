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
    
    private Long id;
    
    private UserResponseDto author;

    private Timestamp posted;

    private String content;

    private TweetResponseDto inReplyTo;

    private TweetResponseDto repostOf;
}
