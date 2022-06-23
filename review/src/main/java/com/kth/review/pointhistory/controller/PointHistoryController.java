package com.kth.review.pointhistory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kth.review.pointhistory.domain.PointHistory;
import com.kth.review.pointhistory.dto.UserPointResponseDTO;
import com.kth.review.pointhistory.service.PointHistoryService;
import com.kth.review.user.domain.User;
import com.kth.review.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PointHistoryController {
	
	private final UserService userService;
	private final PointHistoryService pointHistoryService;
	
	@GetMapping("/user/points")
	public ResponseEntity<?> getPointsOnUser(@RequestBody String userId) throws Exception{
		// 1. user정보 포인트 가져오기
		User user = userService.findByUserId(userId);
		// 2. userId로 History 가져오기
		List<PointHistory> pointHistoryList =  pointHistoryService.findAllByUserId(userId);
	
		return ResponseEntity.status(HttpStatus.OK).body(new UserPointResponseDTO(userId, user.getPoint(), pointHistoryList));
	}
}
