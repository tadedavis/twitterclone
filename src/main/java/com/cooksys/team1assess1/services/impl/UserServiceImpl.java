package com.cooksys.team1assess1.services.impl;

import com.cooksys.team1assess1.dtos.*;
import com.cooksys.team1assess1.entities.User;
import com.cooksys.team1assess1.exceptions.BadRequestException;
import com.cooksys.team1assess1.dtos.UserResponseDto;
import com.cooksys.team1assess1.services.UserService;
import com.cooksys.team1assess1.dtos.TweetResponseDto;
import com.cooksys.team1assess1.exceptions.NotFoundException;
import com.cooksys.team1assess1.mappers.TweetMapper;
import com.cooksys.team1assess1.mappers.UserMapper;
import com.cooksys.team1assess1.repositories.TweetRepository;
import com.cooksys.team1assess1.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	public List<UserResponseDto> getAllUsers() {
		return userMapper.entitiesToDtos(userRepository.getAllNonDeletedUsers());
	}

	@Override
	public UserResponseDto createUser(UserRequestDto userRequestDto) throws BadRequestException {

		CredentialsDto credentials = userRequestDto.getCredentials();

		ProfileDto profile = userRequestDto.getProfile();

		if (credentials == null || profile == null || credentials.getUsername() == null || credentials.getPassword() == null || profile.getEmail() == null) {
			throw new BadRequestException("missing required fields.");
		}

		String username = userRequestDto.getCredentials().getUsername();

		Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);

		if (optionalUser.isPresent()) {
			throw new BadRequestException("Username already taken.");
		}

		User user = userMapper.dtoToEntity(userRequestDto);

		if (userRepository.existsByCredentialsUsername(username)) {
			user.setDeleted(false);
			userRepository.saveAndFlush(user);
			return userMapper.entityToDto(user);
		}

		userRepository.saveAndFlush(user);

		return userMapper.entityToDto(user);
	}

	@Override
	public UserResponseDto updateProfile(UserRequestDto userRequestDto, String username) throws BadRequestException, NotFoundException {

		User userToUpdate = userRepository.findByCredentialsUsernameAndDeletedFalse(username)
				.orElseThrow(() -> new NotFoundException("User does not exist or is deleted."));

		User updatedUser = userMapper.dtoToEntity(userRequestDto);


		if (updatedUser.getProfile() == null) {
			throw new BadRequestException("User has no profile");
		}

		if (!userToUpdate.getCredentials().equals(updatedUser.getCredentials())) {
			throw new BadRequestException("Credentials don't match");
		}

		userToUpdate.setProfile(updatedUser.getProfile());
		userRepository.saveAndFlush(userToUpdate);

		return userMapper.entityToDto(userToUpdate);
	}

	@Override
	public UserResponseDto deleteUser(UserRequestDto userRequestDto, String username) {

		User userToUpdate = userRepository.findByCredentialsUsernameAndDeletedFalse(username)
				.orElseThrow(() -> new NotFoundException("User does not exist or is deleted."));

		User updatedUser = userMapper.dtoToEntity(userRequestDto);

		if (!userToUpdate.getCredentials().equals(updatedUser.getCredentials())) {
			throw new BadRequestException("Credentials don't match");
		}

		userToUpdate.setDeleted(true);
		userRepository.saveAndFlush(userToUpdate);

		return userMapper.entityToDto(userToUpdate);
	}

	@Override
	public List<UserResponseDto> getFollowers(String username) {
		User user = userRepository.findByCredentialsUsernameAndDeletedFalse(username)
				.orElseThrow(() -> new NotFoundException("User does not exist or is deleted."));

		List<User> followers = user.getFollowers();

		List<UserResponseDto> activeFollowers = new ArrayList<>();

		for (User follower : followers) {
			if (!follower.isDeleted()) {
				activeFollowers.add(userMapper.entityToDto(follower));
			}
		}
		return activeFollowers;
	}
}
