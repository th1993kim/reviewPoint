package com.kth.review.event.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kth.review.event.dto.EventRequestDTO;
import com.kth.review.event.enumeration.Action;
import com.kth.review.event.service.EventService;
import com.kth.review.pointhistory.service.PointHistoryService;
import com.kth.review.user.domain.User;
import com.kth.review.user.service.UserService;

@WebMvcTest(EventController.class)
public class EventControllerUnitTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean 
	private EventService eventService;
	
	@MockBean
	private PointHistoryService PointHistoryService;
	
	private User user = User.builder()
							.id(1L)
							.userId("kth")
							.point(10L)
							.build();
	
	@Test
	void insert() throws Exception{
		//ready
		EventRequestDTO eventRequestDTO = new EventRequestDTO();
		eventRequestDTO.setType("t-type");
		eventRequestDTO.setAction(Action.ADD);
		eventRequestDTO.setContent("t-content");
		eventRequestDTO.setReviewId("t-reviewId");
		eventRequestDTO.setPlaceId("t-placeId");
		eventRequestDTO.setUserId("kth");
		eventRequestDTO.setAttachedPhotoIds(new String[] {"t-test","t-test2"});
		
		String content = new ObjectMapper().writeValueAsString(eventRequestDTO);
		
		//given
		given(userService.findByUserId(eventRequestDTO.getUserId())).willReturn(user);
		given(eventService.insert(user, eventRequestDTO)).willReturn(3L);
		
		//when
		ResultActions resultAction = mockMvc.perform(post("/events")
													.contentType(MediaType.APPLICATION_JSON)
													.content(content)
													.accept(MediaType.APPLICATION_JSON));
		
		//then
		resultAction.andExpect(status().isCreated())
					.andExpect(jsonPath("$.point").value(3L))
					.andDo(MockMvcResultHandlers.print());
	}
	
	
}
