package com.kth.review.event.dto;

import com.kth.review.event.enumeration.Action;

import lombok.Data;

@Data
public class EventRequestDTO {
	
	private String type; 				//글타입
	private Action action;				//생성 ADD /수정 MOD /삭제 DELETE
	private String reviewId;			//UUID - 리뷰 id
	private String content;				//리뷰 내용	
	private String[] attachedPhotoIds;  //리뷰 사진들
	private String userId;				//사용자 id 
	private String placeId;				//여행 장소 id
	
}
