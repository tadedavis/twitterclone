package com.cooksys.team1assess1.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6866509948969049580L;
	
	private String message;
}
