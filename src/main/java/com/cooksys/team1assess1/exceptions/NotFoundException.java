package com.cooksys.team1assess1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6866509948969049580L;
	
	private String message;
}
