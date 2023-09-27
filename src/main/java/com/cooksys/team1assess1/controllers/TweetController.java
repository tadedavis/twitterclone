package com.cooksys.team1assess1.controllers;

import com.cooksys.team1assess1.dtos.CredentialsDto;
import com.cooksys.team1assess1.dtos.TweetRequestDto;
import com.cooksys.team1assess1.dtos.TweetResponseDto;
import com.cooksys.team1assess1.dtos.UserResponseDto;
import com.cooksys.team1assess1.services.Context;
import com.cooksys.team1assess1.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {

    private final TweetService tweetService;

    @GetMapping
    public List<TweetResponseDto> getAll(){
        return tweetService.getAllTweets();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponseDto createSimpleTweet(TweetRequestDto tweetRequestDto, CredentialsDto credentialsDto){
        return tweetService.createSimpleTweet(tweetRequestDto, credentialsDto);
    }

    @GetMapping("/{id}/replies")
    public List<TweetResponseDto> getReplies(@PathVariable Long id){
    	return tweetService.getReplies(id);
    }
    
    @GetMapping("/{id}/reposts")
    public List<TweetResponseDto> getReposts(@PathVariable Long id){
    	return tweetService.getReposts(id);
    }
    
    @GetMapping("/{id}/mentions")
    public List<UserResponseDto> getMentions(@PathVariable Long id){
    	return tweetService.getMentions(id);
    }
    
    @GetMapping("/{id}/context")
    public Context getContext(@PathVariable Long id){
    	return tweetService.getContext(id);
    }
}
