package com.cooksys.team1assess1.services;

import com.cooksys.team1assess1.dtos.CredentialsDto;
import com.cooksys.team1assess1.dtos.TweetResponseDto;
import com.cooksys.team1assess1.dtos.UserRequestDto;
import com.cooksys.team1assess1.dtos.UserResponseDto;
import com.cooksys.team1assess1.entities.Credentials;

import java.util.List;


public interface UserService {

	public List<TweetResponseDto> getUserFeed(String username);

	public List<TweetResponseDto> getUserMentions(String username);

    public UserResponseDto getUserByUsername(String username);

	public List<UserResponseDto> getAllUsers();

	public UserResponseDto createUser(UserRequestDto userRequestDto);

	public UserResponseDto updateProfile(UserRequestDto userRequestDto, String username);

	public UserResponseDto deleteUser(CredentialsDto credentialsDto, String username);

	public List<UserResponseDto> getUserFollowers(String username);

	public List<UserResponseDto> getUserFollowing(String username);

	public List<TweetResponseDto> getUserTweets(String username);

	public void follow(Credentials credentials, String username);

	public void unfollow(Credentials credentials, String username);
}
