package com.cooksys.team1assess1.dtos;

import com.cooksys.team1assess1.entities.Hashtag;
import com.cooksys.team1assess1.entities.Tweet;
import com.cooksys.team1assess1.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Data
public class TweetRequestDto {

    private String content;

    private CredentialsDto credentials;
}
