package com.kth.review.event.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kth.review.event.dto.EventRequestDTO;
import com.kth.review.event.enumeration.Action;
import com.kth.review.user.domain.User;
import com.kth.review.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class EventControllerIntegralTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserRepository userRepository; 
	@Autowired
	private EntityManager em;
	
	
	@BeforeEach
	void beforEach() {
		User user = User.builder()
					.userId("kthh")
					.point(10L)
					.build();
		userRepository.save(user);
		
		log.info("user = {}",user);
	}
	
					
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
		
		String content = new ObjectMapper().writeValueAsString(eventRequestDTO);
		
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
