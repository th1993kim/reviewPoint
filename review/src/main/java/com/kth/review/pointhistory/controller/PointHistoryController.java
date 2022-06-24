package com.kth.review.pointhistory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kth.review.pointhistory.dto.UserPointResponseDTO;
import com.kth.review.pointhistory.service.PointHistoryService;
import com.kth.review.user.domain.User;
import com.kth.review.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PointHistoryController {
	
	private final UserService userService;
	@GetMapping("/users/{userId}/points")
	public ResponseEntity<?> getPointsOnUser(@PathVariable(name="userId") String userId) throws Exception{
		// 1. user로 포인트 정보 가져오기
		User user = userService.findByUserIdWithPointHistory(userId);
		
		UserPointResponseDTO userPointResponseDTO = new UserPointResponseDTO(userId, user.getPoint(), user.toPointHistoryResponseDTOList());
		return ResponseEntity.status(HttpStatus.OK).body(userPointResponseDTO);
	}
}
