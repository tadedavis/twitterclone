package com.cooksys.team1assess1.services;

import com.cooksys.team1assess1.dtos.UserResponseDto;

import java.util.List;

import com.cooksys.team1assess1.dtos.TweetResponseDto;

public interface UserService {

	public List<TweetResponseDto> getUserFeed(String username);

	public List<TweetResponseDto> getUserMentions(String username);

    UserResponseDto getuserByUsername(String username);
}
