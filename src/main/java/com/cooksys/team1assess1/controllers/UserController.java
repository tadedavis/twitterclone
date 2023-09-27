package com.cooksys.team1assess1.controllers;

import com.cooksys.team1assess1.entities.Tweet;
import com.cooksys.team1assess1.dtos.*;
import com.cooksys.team1assess1.services.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    
    @GetMapping("/@{username}/feed")
    public List<TweetResponseDto> GetUserFeed(@PathVariable String username){
    	return userService.getUserFeed(username);
    }
    @GetMapping("/@{username}/mentions")
    public List<TweetResponseDto> GetUserMentions(@PathVariable String username){
    	return userService.getUserMentions(username);
    }
}
