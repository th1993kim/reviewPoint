package com.kth.review.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kth.review.pointhistory.domain.PointHistory;
import com.kth.review.pointhistory.repository.PointHistoryRepository;
import com.kth.review.user.domain.User;
import com.kth.review.user.exception.UserNotFoundException;
import com.kth.review.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	public User findByUserIdWithPointHistory(String userId) throws UserNotFoundException {
		return userRepository.findByUserIdWithPointHistory(userId).orElseThrow(() -> new UserNotFoundException());
	}
	public User findByUserId(String userId) throws UserNotFoundException {
		return userRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException());
	}
	
}
