package com.kth.review.pointhistory.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kth.review.pointhistory.domain.PointHistory;
import com.kth.review.user.domain.User;
import com.kth.review.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(PointHistoryController.class)
public class PointHistoryControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	void getPointsOnUser() throws Exception{
		//ready
		String userId = "kth"; 
		User user = User.builder()
						.id(1L)
						.userId(userId)
						.pointHistoryList(new ArrayList<>())
						.point(10L)
						.build();
		
		PointHistory pointHistory = PointHistory.builder()
									.id(1L)
									.user(user)
									.point(2L)
									.build();
		PointHistory pointHistory2 = PointHistory.builder()
												.id(2L)
												.user(user)
												.point(2L)
												.build();

		user.getPointHistoryList().add(pointHistory);
		user.getPointHistoryList().add(pointHistory2);
		
		
		//given
		given(userService.findByUserIdWithPointHistory(userId)).willReturn(user);
		
		
		//when
		ResultActions resultAction = mockMvc.perform(get("/users/kth/points")
													.accept(MediaType.APPLICATION_JSON));
		
		//then
		resultAction.andExpect(status().isOk())
					.andExpect(jsonPath("$.userId").value("kth"))
					.andDo(MockMvcResultHandlers.print());
		
	}
}
