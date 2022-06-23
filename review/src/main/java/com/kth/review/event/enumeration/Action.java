package com.kth.review.event.enumeration;

import lombok.Getter;

public enum Action {
	ADD("입력"),
	MOD("수정"),
	DELETE("삭제");

	@Getter
	private String actionNm;
	
	Action(String actionNm) {
		this.actionNm = actionNm;
	}
	
}
