package com.cooksys.team1assess1.services;

import com.cooksys.team1assess1.dtos.CredentialsDto;
import com.cooksys.team1assess1.dtos.TweetRequestDto;
import com.cooksys.team1assess1.dtos.TweetResponseDto;
import com.cooksys.team1assess1.dtos.UserResponseDto;
import com.cooksys.team1assess1.entities.Tweet;

import java.util.List;

public interface TweetService {
    List<TweetResponseDto> getAllTweets();

    TweetResponseDto createSimpleTweet(TweetRequestDto tweetRequestDto, CredentialsDto credentialsDto);

	List<TweetResponseDto> getReplies(Long id);

	List<TweetResponseDto> getReposts(Long id);

	List<UserResponseDto> getMentions(Long id);

	Context getContext(Long id);
}
