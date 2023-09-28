package com.cooksys.team1assess1.services;

import com.cooksys.team1assess1.dtos.TweetResponseDto;
import com.cooksys.team1assess1.dtos.UserRequestDto;
import com.cooksys.team1assess1.dtos.UserResponseDto;

import java.util.List;


public interface UserService {

	public List<TweetResponseDto> getUserFeed(String username);

	public List<TweetResponseDto> getUserMentions(String username);

    UserResponseDto getuserByUsername(String username);

	public List<UserResponseDto> getAllUsers();

	public UserResponseDto createUser(UserRequestDto userRequestDto);

	public UserResponseDto updateProfile(UserRequestDto userRequestDto, String username);

	public UserResponseDto deleteUser(UserRequestDto user, String username);
}
