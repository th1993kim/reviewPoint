package com.kth.review.event.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kth.review.common.error.ErrorResult;

@RestControllerAdvice("com.kth.review.event.controller")
public class EventExceptionAdvice {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ErrorResult exceptionHandler(Exception e) {
		return new ErrorResult(HttpStatus.BAD_REQUEST.value(),e.getMessage());
	}
	
}
