package com.kth.review.user.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "사용자를 찾을 수 없습니다.";
	}
	
}
