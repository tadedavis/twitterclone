package com.cooksys.team1assess1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.team1assess1.services.HashtagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class HashtagController {
	private final HashtagService hashtagService;

}
