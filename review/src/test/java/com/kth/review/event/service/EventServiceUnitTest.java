package com.kth.review.event.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kth.review.event.domain.Event;
import com.kth.review.event.dto.EventRequestDTO;
import com.kth.review.event.enumeration.Action;
import com.kth.review.event.repository.EventRepository;
import com.kth.review.user.domain.User;

@ExtendWith(MockitoExtension.class)
public class EventServiceUnitTest {
	
	@InjectMocks
	private EventService eventService;
	
	@Mock 
	private EventRepository eventRepository;
	
	private User user = User.builder()
							.id(1L)
							.userId("kth")
							.point(10L)
							.build();

	private Event event = Event.builder()
								 .type("t-type")
								 .action(Action.ADD)
								 .reviewId("t-reviewId")
								 .content("t-content")
								 .attachedPhotoIds(String.join(",",new String[] {"t-test","t-test2"}))
								 .user(user)
								 .placeId("t-placeId")
								 .isFirst(true)
								 .build();
	@Test
	void insert() throws Exception {
		//ready
		EventRequestDTO eventRequestDTO = new EventRequestDTO();
		eventRequestDTO.setType("t-type");
		eventRequestDTO.setAction(Action.ADD);
		eventRequestDTO.setContent("t-content");
		eventRequestDTO.setReviewId("t-reviewId");
		eventRequestDTO.setPlaceId("t-placeId");
		eventRequestDTO.setUserId("kth");
		eventRequestDTO.setAttachedPhotoIds(new String[] {"t-test","t-test2"});
		Optional<Event> findEvent = Optional.ofNullable(null);
		
		//given
		given(eventRepository.findByUserAndPlaceIdAndActionNot(user, eventRequestDTO.getPlaceId(), Action.DELETE)).willReturn(findEvent);
		given(eventRepository.countByPlaceIdAndActionNot(eventRequestDTO.getPlaceId(), Action.DELETE)).willReturn(0L);
		given(eventRepository.save(any())).willReturn(event);
		
		//when
		Long result = eventService.insert(user, eventRequestDTO);
		
		//then
		assertThat(result).isEqualTo(3L);
	}
	
	@Test
	void update() throws Exception{
		//ready
		EventRequestDTO eventRequestDTO = new EventRequestDTO();
		eventRequestDTO.setAction(Action.ADD);
		eventRequestDTO.setContent("");
		eventRequestDTO.setReviewId("t-reviewId");
		eventRequestDTO.setUserId("kth");
		eventRequestDTO.setAttachedPhotoIds(new String[] {});
		Optional<Event> findEvent = Optional.of(event);
		
		//given
		given(eventRepository.findByReviewIdAndActionNot(eventRequestDTO.getReviewId(), Action.DELETE)).willReturn(findEvent);
		
		//when
		Long result = eventService.update(eventRequestDTO);
		
		//then
		assertThat(result).isEqualTo(-2L);
	}
	
	@Test
	void delete() throws Exception{
		//ready
		EventRequestDTO eventRequestDTO = new EventRequestDTO();
		eventRequestDTO.setAction(Action.DELETE);
		eventRequestDTO.setReviewId("t-reviewId");
		eventRequestDTO.setUserId("kth");
		Optional<Event> findEvent = Optional.of(event);
		
		//given
		given(eventRepository.findByReviewIdAndActionNot(eventRequestDTO.getReviewId(), Action.DELETE)).willReturn(findEvent);
		
		//when
		Long result = eventService.delete(eventRequestDTO);
		
		//then
		assertThat(result).isEqualTo(-3L);
	}
	
	
	
	
}
