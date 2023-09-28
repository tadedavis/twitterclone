package com.cooksys.team1assess1.services.impl;

import com.cooksys.team1assess1.dtos.*;
import com.cooksys.team1assess1.entities.Hashtag;
import com.cooksys.team1assess1.entities.Tweet;
import com.cooksys.team1assess1.entities.User;
import com.cooksys.team1assess1.exceptions.BadRequestException;
import com.cooksys.team1assess1.mappers.HashtagMapper;
import com.cooksys.team1assess1.mappers.TweetMapper;
import com.cooksys.team1assess1.mappers.UserMapper;
import com.cooksys.team1assess1.repositories.HashtagRepository;
import com.cooksys.team1assess1.repositories.TweetRepository;
import com.cooksys.team1assess1.repositories.UserRepository;
import com.cooksys.team1assess1.exceptions.NotFoundException;
import com.cooksys.team1assess1.services.Context;
import com.cooksys.team1assess1.services.TweetService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
	private final TweetMapper tweetMapper;
	private final TweetRepository tweetRepository;
	private final UserMapper userMapper;
	private final UserRepository userRepository;

	private final HashtagMapper hashtagMapper;
	private final HashtagRepository hashtagRepository;

	@Override
	public List<TweetResponseDto> getAllTweets() {
	    return tweetRepository.getAllNonDeletedTweets();
	}

	@Override
	public TweetResponseDto createSimpleTweet(TweetRequestDto tweetRequestDto) {
		Tweet tweet = new Tweet();
		tweet.setContent(tweetRequestDto.getContent());
		if(tweet.getContent() == null || tweet.getInReplyTo() != null || tweet.getRepostOf() != null) throw new BadRequestException("Invalid body");
		Pattern mentionPat = Pattern.compile("@\\w+");
		Matcher userMatcher = mentionPat.matcher(tweet.getContent());
		while(userMatcher.find()) {
			User user = userRepository.findByCredentialsUsername(userMatcher.group(1));
			if(user != null)
				tweet.getMentions().add(user);
		}
		Pattern tagPat = Pattern.compile("#\\w+");
		Matcher tagMatcher = tagPat.matcher(tweet.getContent());
		while(tagMatcher.find()){
			Hashtag hashtag;
			if(!hashtagRepository.existsByLabel(tagMatcher.group(1))) hashtag = new Hashtag();
			else hashtag = hashtagRepository.findByLabel(tagMatcher.group(1));
			hashtag.getTweets().add(tweet);
			tweet.getHashtags().add(hashtag);
		}
		tweetRepository.saveAndFlush(tweet);
		return tweetMapper.entityToDto(tweet);
	}

	@Override
	public TweetResponseDto getTweet(Long id){
		Tweet tweet = tweetRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No tweet with given id"));
		if(tweet.isDeleted()) throw new NotFoundException("The tweet you are looking for has been deleted");
		return tweetMapper.entityToDto(tweet);
	}

	@Override
	public List<HashtagDto> getTweetTags(Long id) {
		Tweet tweet = tweetRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No tweet with given id"));
		if(tweet.isDeleted()) throw new NotFoundException("The tweet you are looking for has been deleted");
		return hashtagMapper.entitiesToDtos(tweet.getHashtags());
	}

	@Override
	public List<UserResponseDto> getUserLikes(Long id) {
		Tweet tweet = tweetRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No tweet with given id"));
		if(tweet.isDeleted()) throw new NotFoundException("The tweet you are looking for has been deleted");
		return userMapper.entitiesToDtos(tweet.getLikes());
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
