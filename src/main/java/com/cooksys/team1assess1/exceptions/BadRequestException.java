package com.cooksys.team1assess1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3030692963873945458L;
	
	private String message;

}
