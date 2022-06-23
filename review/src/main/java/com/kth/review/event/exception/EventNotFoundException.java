package com.kth.review.event.exception;

public class EventNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "해당 이벤트를 찾을 수 없습니다.";
	}
	
	

}
