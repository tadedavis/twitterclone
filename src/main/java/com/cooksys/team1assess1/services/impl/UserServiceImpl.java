package com.cooksys.team1assess1.services.impl;

import com.cooksys.team1assess1.dtos.UserResponseDto;
import com.cooksys.team1assess1.services.UserService;
import com.cooksys.team1assess1.dtos.TweetResponseDto;
import com.cooksys.team1assess1.entities.*;
import com.cooksys.team1assess1.exceptions.NotFoundException;
import com.cooksys.team1assess1.mappers.TweetMapper;
import com.cooksys.team1assess1.mappers.UserMapper;
import com.cooksys.team1assess1.repositories.TweetRepository;
import com.cooksys.team1assess1.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final TweetRepository tweetRepository;

	private final UserMapper userMapper;
	private final TweetMapper tweetMapper;

	@Override
	public List<TweetResponseDto> getUserFeed(String username) {
		User user = userRepository.findByCredentialsUsernameAndDeletedFalse(username)
		            .orElseThrow(() -> new NotFoundException("User not found or has been deleted."));
		List<User> authors = new ArrayList<>(user.getFollowing());
		authors.add(user);

		return tweetMapper.entitiesToDtos(tweetRepository.findByAuthorInAndDeletedFalseOrderByPostedDesc(authors));
	}

	@Override
	public List<TweetResponseDto> getUserMentions(String username) {
		User user =  userRepository.findByCredentialsUsernameAndDeletedFalse(username)
				.orElseThrow(() -> new NotFoundException("User not found or has been deleted."));
		return tweetMapper.entitiesToDtos(user.getMentionedBy());
	}


    @Override
    public UserResponseDto getuserByUsername(String username) {
        User user = userRepository.findByCredentialsUsername(username);
        return userMapper.entityToDto(user);
    }
}
