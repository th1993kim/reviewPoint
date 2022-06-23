package com.kth.review.point.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kth.review.point.domain.PointHistory;
import com.kth.review.point.repository.PointHistoryRepository;
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
	public void insert(String userId, Long point) throws UserNotFoundException {
		
		PointHistory pointHistory = PointHistory.builder()
												.userId(userId)
												.point(point)
												.build();
		
		User user = userRepository.findByUserId(userId).orElse(null);
		if(user !=null) user.setPoint(user.getPoint()+point);
		pointHistoryRepository.save(pointHistory);
	}

}
