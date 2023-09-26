package com.cooksys.team1assess1.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cooksys.team1assess1.dtos.ErrorDto;
import com.cooksys.team1assess1.exceptions.BadRequestException;
import com.cooksys.team1assess1.exceptions.NotAuthorizedException;
import com.cooksys.team1assess1.exceptions.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = {"com.cooksys.team1assess1.controllers"})
@ResponseBody
public class Assess1ControllerAdvice {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public ErrorDto handleBadRequestException(HttpServletRequest request,BadRequestException badrequest) {
		return new ErrorDto(badrequest.getMessage());
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(NotAuthorizedException.class)
	public ErrorDto handleNotAuthorizedException(HttpServletRequest request,NotAuthorizedException notAuthorized) {
		return new ErrorDto(notAuthorized.getMessage());
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ErrorDto handleNotFoundException(HttpServletRequest request,NotFoundException notFound) {
		return new ErrorDto(notFound.getMessage());
	}
}
