package com.kth.review.event.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kth.review.event.dto.EventRequestDTO;
import com.kth.review.event.dto.EventResponseDTO;
import com.kth.review.event.service.EventService;
import com.kth.review.pointhistory.service.PointHistoryService;
import com.kth.review.user.domain.User;
import com.kth.review.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EventController {

	private final EventService eventService;
	private final PointHistoryService pointHistoryService;
	private final UserService userService;
	
	@PostMapping("/events")
	public ResponseEntity<?> postEvent(@RequestBody EventRequestDTO eventRequestDTO) throws Exception{
		
		// 1. 각 변수 준비 ( User Entity가 존재하지 않을경우 예외 발생 )
		Long point = 0L;
		String status = "";
		HttpStatus httpStatus = HttpStatus.OK;
		User user = userService.findByUserId(eventRequestDTO.getUserId());
		
		// 2. Action별로 로직 및 Point제도 다르게 해서 가져오기
		switch(eventRequestDTO.getAction()){
			case ADD 	: point = eventService.insert(user,eventRequestDTO); httpStatus = HttpStatus.CREATED; break;
			case MOD 	: point = eventService.update(eventRequestDTO); break;
			case DELETE : point = eventService.delete(eventRequestDTO); break;
			default 	: break;
		}
		
		// 3. 포인트 변동 있을 시에만 포인트 입력
		if(point != 0L) pointHistoryService.insert(user,point);
		
		if	   ( point == 0L )  status = "변동없음";
		else if( point > 0L )   status = "추가";
		else 					status = "차감"; 
		
		// 4. 결과 내용 status:포인트 변동사항   point:변동된 포인트 점수
		
		return ResponseEntity.status(httpStatus).body(new EventResponseDTO(status,point));
	}
	
}
