package com.kth.review.pointhistory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kth.review.pointhistory.domain.PointHistory;
import com.kth.review.pointhistory.repository.PointHistoryRepository;
import com.kth.review.user.domain.User;
import com.kth.review.user.exception.UserNotFoundException;
import com.kth.review.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PointHistoryService {
	
	private final PointHistoryRepository pointHistoryRepository;
	private final UserRepository userRepository;
	
	@Transactional
	public void insert(User user, Long point) throws UserNotFoundException {
		
		PointHistory pointHistory = PointHistory.builder()
												.userId(user.getUserId())
												.point(point)
												.build();
		
		user.setPoint(user.getPoint()+point);
		pointHistoryRepository.save(pointHistory);
	}

	public List<PointHistory> findAllByUserId(String userId) { 
		return pointHistoryRepository.findAllByUserId(userId); 
	}
	


}
