package com.cooksys.team1assess1.services;

import com.cooksys.team1assess1.dtos.*;
import com.cooksys.team1assess1.entities.Tweet;

import java.util.List;

public interface TweetService {
    List<TweetResponseDto> getAllTweets();

    TweetResponseDto createSimpleTweet(TweetRequestDto tweetRequestDto);

	TweetResponseDto getTweet(Long id);

	List<HashtagDto> getTweetTags(Long id);

	List<UserResponseDto> getUserLikes(Long id);

	List<TweetResponseDto> getReplies(Long id);

	List<TweetResponseDto> getReposts(Long id);

	List<UserResponseDto> getMentions(Long id);

	Context getContext(Long id);
}
