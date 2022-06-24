package com.kth.review.event.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kth.review.event.domain.Event;
import com.kth.review.event.dto.EventRequestDTO;
import com.kth.review.event.enumeration.Action;
import com.kth.review.event.exception.EventNotFoundException;
import com.kth.review.event.exception.EventOverLapException;
import com.kth.review.event.repository.EventRepository;
import com.kth.review.user.domain.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class EventService {

	private final EventRepository eventRepository;
	
	public Long insert(User user, EventRequestDTO eventRequestDTO) throws Exception {
		Long point = 0L;
		Boolean isFirst = false;
		Event event = eventRepository.findByUserAndPlaceIdAndActionNot(user,eventRequestDTO.getPlaceId(),Action.DELETE).orElse(null);
		Long count = eventRepository.countByPlaceIdAndActionNot(eventRequestDTO.getPlaceId(),Action.DELETE);
		
		if(event == null) {
			/*
			 * 포인트 추가 정책
			 * 1. 내용 1자 이상시 1점
			 * 2. 사진 1장 이상시 1점
			 * 3. 해당 장소에 최초 리뷰 등록시 1점 
			 */
			if( eventRequestDTO.getContent().length()>0) point++;
			if( eventRequestDTO.getAttachedPhotoIds().length>0) point++;
			if( count < 1) {
				point++;
				isFirst = true;
			}
			event = Event.builder()
						 .type(eventRequestDTO.getType())
						 .action(eventRequestDTO.getAction())
						 .reviewId(eventRequestDTO.getReviewId())
						 .content(eventRequestDTO.getContent())
						 .attachedPhotoIds(String.join(",",eventRequestDTO.getAttachedPhotoIds()))
						 .user(user)
						 .placeId(eventRequestDTO.getPlaceId())
						 .isFirst(isFirst)
						 .build();
			eventRepository.save(event);
		}else throw new EventOverLapException();
		
		return point;
	}

	public Long update(EventRequestDTO eventRequestDTO) throws Exception {
		
		Event event = eventRepository.findByReviewIdAndActionNot(eventRequestDTO.getReviewId(),Action.DELETE).orElseThrow(() -> new EventNotFoundException());
		Long point = 0L;
		String[] eventAttachedPhotoIds = event.getAttachedPhotoIds().split(",");
		
		//수정전 글 내용 비교
		if( event.getContent().length()>0) {
			if( eventRequestDTO.getContent().length()<1) point--;
		}else{ 
			if( eventRequestDTO.getContent().length()>0) point++;
		}
		
		//수정전 글과 사진 수량 비교
		if( eventAttachedPhotoIds.length >0) { 
			if( eventRequestDTO.getAttachedPhotoIds().length<1 ) point--;
		}else { 
			if( eventRequestDTO.getAttachedPhotoIds().length>0 ) point++;
		}
		
		event.setAction(eventRequestDTO.getAction());
		event.setContent(eventRequestDTO.getContent());
		event.setAttachedPhotoIds(String.join(",", eventRequestDTO.getAttachedPhotoIds()));
		eventRepository.save(event);
		return point;
	}

	public Long delete(EventRequestDTO eventRequestDTO) throws Exception{
		
		Long point = 0L;
		Event event = eventRepository.findByReviewIdAndActionNot(eventRequestDTO.getReviewId(),Action.DELETE).orElseThrow(() -> new EventNotFoundException());
		String[] eventAttachedPhotoIds = event.getAttachedPhotoIds().split(",");
		
		if( event.getIsFirst() ) 			  point --;
		if( event.getContent().length()  >0 ) point --;	
		if( eventAttachedPhotoIds.length >0 ) point --;
		
		event.setAction(eventRequestDTO.getAction());
		eventRepository.save(event);
		
		return point;
	}
	
}
