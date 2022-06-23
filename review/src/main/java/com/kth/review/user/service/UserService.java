package com.kth.review.user.service;

import org.springframework.stereotype.Service;

import com.kth.review.user.domain.User;
import com.kth.review.user.exception.UserNotFoundException;
import com.kth.review.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public User findByUserId(String userId) throws UserNotFoundException {
		return userRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException());
	}
	
}
