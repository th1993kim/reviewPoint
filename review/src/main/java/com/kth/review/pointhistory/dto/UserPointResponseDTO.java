package com.kth.review.pointhistory.dto;

import java.util.List;

import com.kth.review.pointhistory.domain.PointHistory;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserPointResponseDTO {
	private String userId;
	private Long point;
	private List<PointHistory> pointHistoryList;
}
