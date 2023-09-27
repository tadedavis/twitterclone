package com.cooksys.team1assess1.services.impl;

import com.cooksys.team1assess1.dtos.CredentialsDto;
import com.cooksys.team1assess1.dtos.TweetRequestDto;
import com.cooksys.team1assess1.dtos.TweetResponseDto;
import com.cooksys.team1assess1.services.TweetService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {@Override
	public List<TweetResponseDto> getAllTweets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto createSimpleTweet(TweetRequestDto tweetRequestDto, CredentialsDto credentialsDto) {
		// TODO Auto-generated method stub
		return null;
	}
}
