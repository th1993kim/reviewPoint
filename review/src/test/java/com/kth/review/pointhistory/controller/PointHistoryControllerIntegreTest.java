package com.kth.review.pointhistory.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import com.kth.review.pointhistory.domain.PointHistory;
import com.kth.review.pointhistory.repository.PointHistoryRepository;
import com.kth.review.user.domain.User;
import com.kth.review.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class PointHistoryControllerIntegreTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PointHistoryRepository  pointHistoryRepository;
	
	@Autowired
	private EntityManager em;
	
	@BeforeEach
	void beforeEach() {
		em.createNativeQuery("ALTER TABLE user AUTO_INCREMENT 1").executeUpdate();
		em.createNativeQuery("ALTER TABLE point_history AUTO_INCREMENT 1").executeUpdate();
		
		User user = User.builder()
						.userId("kth")
						.point(10L)
						.build();
		userRepository.save(user);
		
		List<PointHistory> pointHistoryList = new ArrayList<>();
		pointHistoryList.add(PointHistory.builder()
										.user(user)
										.point(1L)
										.build());
		pointHistoryList.add(PointHistory.builder()
										.user(user)
										.point(2L)
										.build());
		
		pointHistoryRepository.saveAllAndFlush(pointHistoryList);
		
		em.clear();
	}
	
	
	@Test
	void getPointsOnUser() throws Exception{
		
		//when
		ResultActions resultAction = mockMvc.perform(get("/users/kth/points")
													.accept(MediaType.APPLICATION_JSON));
		
		//then
		resultAction.andExpect(status().isOk())
					.andExpect(jsonPath("$.userId").value("kth"))
					.andDo(MockMvcResultHandlers.print());
	}
}
