package com.kth.review.event.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kth.review.event.enumeration.Action;

import lombok.Data;

@Data
public class EventRequestDTO {
	
	@NotBlank(message = "type값이 비어있습니다.")
	private String type; 				//글타입
	
	@NotBlank(message = "action값이 비어있습니다.")
	private Action action;				//생성 ADD /수정 MOD /삭제 DELETE
	
	@NotBlank(message = "reviewId값이 비어있습니다.")
	private String reviewId;			//UUID - 리뷰 id
	
	private String content;				//리뷰 내용	
	private String[] attachedPhotoIds;  //리뷰 사진들
	
	@NotBlank(message = "userId값이 비어있습니다.")
	private String userId;				//사용자 id
	
	@NotBlank(message = "placeId값이 비어있습니다.")
	private String placeId;				//여행 장소 id
	
}
