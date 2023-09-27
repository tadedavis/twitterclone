package com.cooksys.team1assess1.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cooksys.team1assess1.services.HashtagService;
import com.cooksys.team1assess1.entities.Hashtag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class HashtagController {
	private final HashtagService hashtagService;
	
	
	@GetMapping
	public List<Hashtag> GetTags(){
		return hashtagService.getTags();
	}
}
