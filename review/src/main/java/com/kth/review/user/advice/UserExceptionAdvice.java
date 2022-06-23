package com.kth.review.user.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kth.review.common.error.ErrorResult;
import com.kth.review.user.exception.UserNotFoundException;

@RestControllerAdvice("com.kth.review")
public class UserExceptionAdvice {
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UserNotFoundException.class)
	public ErrorResult userNotFoundExHandler(UserNotFoundException e) {
		return new ErrorResult(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
	}
}
