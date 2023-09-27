package com.cooksys.team1assess1.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.team1assess1.repositories.HashtagRepository;
import com.cooksys.team1assess1.repositories.UserRepository;
import com.cooksys.team1assess1.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService{
	
	private final HashtagRepository hashtagRepository;
	private final UserRepository userRepository;
	@Override
	public boolean ValidateTag(String label) {
		return hashtagRepository.existsByLabel(label);
	}
	@Override
	public boolean ValidateUserExists(String username) {
		return userRepository.existsByCredentialsUsername(username);
	}
	@Override
	public boolean ValidateUserAvaliable(String username) {
		return !userRepository.existsByCredentialsUsername(username);
	}

}
