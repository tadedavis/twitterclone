package com.cooksys.team1assess1.services.impl;

import com.cooksys.team1assess1.dtos.CredentialsDto;
import com.cooksys.team1assess1.dtos.TweetRequestDto;
import com.cooksys.team1assess1.dtos.TweetResponseDto;
import com.cooksys.team1assess1.dtos.UserResponseDto;
import com.cooksys.team1assess1.entities.Tweet;
import com.cooksys.team1assess1.exceptions.NotFoundException;
import com.cooksys.team1assess1.mappers.TweetMapper;
import com.cooksys.team1assess1.mappers.UserMapper;
import com.cooksys.team1assess1.repositories.TweetRepository;
import com.cooksys.team1assess1.services.Context;
import com.cooksys.team1assess1.services.TweetService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
	
	private final TweetRepository tweetRepository;
	private final TweetMapper tweetMapper;
	
	private final UserMapper userMapper;
	@Override
	public List<TweetResponseDto> getAllTweets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto createSimpleTweet(TweetRequestDto tweetRequestDto, CredentialsDto credentialsDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getReplies(Long id) {
		Tweet tweet = tweetRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No tweet with given id"));
		return tweetMapper.entitiesToDtos(tweet.getRepliedBy());
	}

	@Override
	public List<TweetResponseDto> getReposts(Long id) {
		Tweet tweet = tweetRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No tweet with given id"));
		return tweetMapper.entitiesToDtos(tweet.getRepostTweets());
	}

	@Override
	public List<UserResponseDto> getMentions(Long id) {
		Tweet tweet = tweetRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No tweet with given id"));
		return userMapper.entitiesToDtos(tweet.getMentions());
	}

	@Override
	public Context getContext(Long id) {
		Tweet target = tweetRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Tweet not found."));

		Context context = new Context();
		context.setTarget(tweetMapper.entityToDto(target));
		context.setBefore(tweetMapper.entitiesToDtos(getBefore(target)));
		context.setAfter(tweetMapper.entitiesToDtos(getAfter(target)));

		return context;
	}

	private List<Tweet> getBefore(Tweet target) {
        List<Tweet> before = new ArrayList<>();
        Tweet parent = target.getInReplyTo();
        while (parent != null) {
        	if (!parent.isDeleted()) {
        		before.add(0, parent);
        	}
            parent = parent.getInReplyTo();
        }
        return before;
	}

	private List<Tweet> getAfter(Tweet target) {
        List<Tweet> after = new ArrayList<>();
        List<Tweet> replies = tweetRepository.findByInReplyToOrderByPosted(target);
        for (Tweet reply : replies) {
        	if(!reply.isDeleted()) {
        		after.add(reply);
        	}
            after.addAll(getAfter(reply));
        }
        return replies;
	}
}
