package com.kth.review.pointhistory.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kth.review.pointhistory.repository.PointHistoryRepository;
import com.kth.review.user.domain.User;

@ExtendWith(MockitoExtension.class)
public class PointHistoryServiceUnitTest {
	
	@InjectMocks
	private PointHistoryService pointHistoryService;
	
	@Mock
	private PointHistoryRepository pointHistoryRepository;
	
	private User user = User.builder()
							.id(1L)
							.userId("kth")
							.point(10L)
							.build();	
	
	@Test
	void insert() throws Exception{
		Long point = 3L;
		
		pointHistoryService.insert(user, point);
	}
	
}
