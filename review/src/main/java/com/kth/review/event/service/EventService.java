package com.kth.review.event.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kth.review.event.domain.Event;
import com.kth.review.event.dto.EventRequestDTO;
import com.kth.review.event.exception.EventNotFoundException;
import com.kth.review.event.exception.EventOverLapException;
import com.kth.review.event.repository.EventRepository;
import com.kth.review.point.service.PointHistoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventService {

	private final EventRepository eventRepository;
	private final PointHistoryService pointHistoryService;
	
	public Long insert(EventRequestDTO eventRequestDTO) throws Exception {
		Long point = 0L;
		Event event = eventRepository.findOneByUserIdAndPlaceId(eventRequestDTO.getUserId(),eventRequestDTO.getPlaceId()).orElse(null);
		List<Event> eventList = eventRepository.findAllByPlaceIdOrderByRegDtmAsc(eventRequestDTO.getPlaceId());
		
		if(event == null) {
			/*
			 * 포인트 추가 정책
			 * 1. 내용 1자 이상시 1점
			 * 2. 사진 1장 이상시 1점
			 * 3. 해당 장소에 최초 리뷰 등록시 1점 
			 */
			if( eventRequestDTO.getContent().length()>0) point++;
			if( eventRequestDTO.getAttachedPhotoIds().length>0) point++;
			if( eventList.size()<1) point++;
			
			event = Event.builder()
						 .type(eventRequestDTO.getType())
						 .action(eventRequestDTO.getAction())
						 .reviewId(eventRequestDTO.getReviewId())
						 .content(eventRequestDTO.getContent())
						 .attachedPhotoIds(String.join(",",eventRequestDTO.getAttachedPhotoIds()))
						 .userId(eventRequestDTO.getUserId())
						 .placeId(eventRequestDTO.getPlaceId())
						 .build();
			
			if(point != 0L) pointHistoryService.insert(eventRequestDTO.getUserId(),point);
		}else throw new EventOverLapException();
		
		return point;
	}

	public Long update(EventRequestDTO eventRequestDTO) throws Exception {
		
		Event event = eventRepository.findOneByReviewId(eventRequestDTO.getReviewId()).orElseThrow(() -> new EventNotFoundException());
		Long point = 0L;
		String[] eventAttachedPhotoIds = event.getAttachedPhotoIds().split(",");
		
		if( event.getContent().length()>0) {
			point++;
			if( eventRequestDTO.getContent().length()<1) point--;
		}else 
			if( eventRequestDTO.getContent().length()>0) point++;
		
		
		if( eventAttachedPhotoIds.length >0) {
			point++;
			if( eventRequestDTO.getAttachedPhotoIds().length<1 ) point--;
		}else 
			if( eventRequestDTO.getAttachedPhotoIds().length>0 ) point++;
		
		
		if(point != 0L) pointHistoryService.insert(eventRequestDTO.getUserId(),point);
		
		event.setAction(eventRequestDTO.getAction());
		event.setContent(eventRequestDTO.getContent());
		event.setAttachedPhotoIds(String.join(",", eventRequestDTO.getAttachedPhotoIds()));
		eventRepository.save(event);
		return point;
	}

	public Long delete(EventRequestDTO eventRequestDTO) throws Exception{
		
		Long point = 0L;
		Event event = eventRepository.findOneByReviewId(eventRequestDTO.getReviewId()).orElseThrow(() -> new EventNotFoundException());
		List<Event> eventList= eventRepository.findAllByPlaceIdOrderByRegDtmAsc(eventRequestDTO.getPlaceId());
		String[] eventAttachedPhotoIds = event.getAttachedPhotoIds().split(",");
		
		if( event.equals(eventList.get(0)) ) point --;
		if( event.getContent().length()  >0 ) point --;	
		if( eventAttachedPhotoIds.length >0 ) point --;
		
		if(point != 0L) pointHistoryService.insert(eventRequestDTO.getUserId(),point);
		
		event.setAction(eventRequestDTO.getAction());
		eventRepository.save(event);
		return point;
	}
	
}
