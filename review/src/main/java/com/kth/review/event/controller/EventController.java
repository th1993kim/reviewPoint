package com.kth.review.event.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kth.review.event.dto.EventRequestDTO;
import com.kth.review.event.service.EventService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EventController {

	private final EventService eventService;
	
	@PostMapping("/events")
	public ResponseEntity<?> postEvent(@RequestBody EventRequestDTO eventRequestDTO) throws Exception{
		Long point = 0L;
		String status = "";
		Map<String,Object> result = new HashMap<String,Object>();
		
		switch(eventRequestDTO.getAction()){
			case ADD 	: point = eventService.insert(eventRequestDTO); break;
			case MOD 	: point = eventService.update(eventRequestDTO); break;
			case DELETE : point = eventService.delete(eventRequestDTO); break;
			default 	: break;
		}
		
		if	   ( point == 0L )  status = "변동없음";
		else if( point > 0L )   status = "추가";
		else 					status = "차감"; 
		
		result.put("status", status);
		result.put("point", point);
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
}
