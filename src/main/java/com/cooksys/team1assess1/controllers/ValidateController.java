package com.cooksys.team1assess1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.team1assess1.services.ValidateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {
	private final ValidateService validateService;
}
