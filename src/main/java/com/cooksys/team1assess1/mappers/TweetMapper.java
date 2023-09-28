package com.cooksys.team1assess1.mappers;


import com.cooksys.team1assess1.dtos.TweetRequestDto;
import com.cooksys.team1assess1.dtos.TweetResponseDto;
import com.cooksys.team1assess1.entities.Hashtag;
import com.cooksys.team1assess1.entities.Tweet;
import com.cooksys.team1assess1.entities.User;
import com.cooksys.team1assess1.repositories.TweetRepository;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TweetMapper {
    TweetResponseDto entityToDto(Tweet entity);
    List<TweetResponseDto> entitiesToDtos(List<Tweet> entity);
    Tweet dtoToEntity(TweetRequestDto tweetRequestDto);

}
