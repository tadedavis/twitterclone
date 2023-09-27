package com.cooksys.team1assess1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.team1assess1.services.ValidateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {
	private final ValidateService validateService;
	
	@GetMapping("/tag/exists/{label}")
	public boolean ValidateTag(@PathVariable String label) {
		return validateService.ValidateTag(label);
	}
	
	@GetMapping("/username/exists/@{username}")
	public boolean ValidateUsernameExists(@PathVariable String username) {
		return validateService.ValidateUserExists(username);
	}
	
	@GetMapping("/username/available/@{username}")
	public boolean ValidateUsernameAvailable(@PathVariable String username) {
		return validateService.ValidateUserAvaliable(username);
	}
}
