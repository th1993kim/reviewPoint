package com.kth.review.event.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kth.review.event.domain.Event;
import com.kth.review.event.enumeration.Action;
import com.kth.review.user.domain.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) //mysql DB이용
public class EventRepositoryUnitTest {
	
	@Autowired private EventRepository eventRepository;
	
	private User user = User.builder()
							.id(1L)
							.userId("kth")
							.point(10L)
							.build();
	
	
	@BeforeEach
	void beforeEach() {
		Event event = Event.builder()
				 .type("t-type")
				 .action(Action.ADD)
				 .reviewId("t-reviewId")
				 .content("t-content")
				 .attachedPhotoIds(String.join(",",new String[] {"t-test","t-test2"}))
				 .user(user)
				 .placeId("t-placeId")
				 .isFirst(true)
				 .build();
		eventRepository.save(event);
	}
	
	@Test
	void save() {
		//given
		Event event = Event.builder()
							 .type("type")
							 .action(Action.ADD)
							 .reviewId("reviewId")
							 .content("content")
							 .attachedPhotoIds(String.join(",",new String[] {"test","test2"}))
							 .user(user)
							 .placeId("placeId")
							 .isFirst(true)
							 .build();
		
		//when
		eventRepository.save(event);
		
		//then
		assertThat(event.getId()).isNotNull();
		
		
	}
	
	@Test
	void findByReviewId() {
		//when
		Event event = eventRepository.findByReviewIdAndActionNot("t-reviewId",Action.DELETE).orElseThrow();
		
		//then
		assertThat(event.getPlaceId()).isEqualTo("t-placeId");
	}
	
	@Test
	void findByUserIdAndPlaceIdAndActionNot() {
		//when
		Event event = eventRepository.findByUserAndPlaceIdAndActionNot(user, "t-placeId", Action.DELETE).orElseThrow();
		
		//then
		assertThat(event.getContent()).isEqualTo("t-content");
	}
	
	@Test
	void countByPlaceIdAndActionNot() {
		//when
		Long count = eventRepository.countByPlaceIdAndActionNot("t-placeId",Action.DELETE);
		
		//then
		assertThat(count).isEqualTo(1L);
	}
	
}
