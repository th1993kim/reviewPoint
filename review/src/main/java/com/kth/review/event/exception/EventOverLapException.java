package com.kth.review.event.exception;

public class EventOverLapException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "이벤트가 중복되었습니다.";
	}

	
}
