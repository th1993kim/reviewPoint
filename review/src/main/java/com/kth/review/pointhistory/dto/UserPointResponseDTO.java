package com.kth.review.pointhistory.dto;

import java.util.ArrayList;
import java.util.List;

import com.kth.review.pointhistory.domain.PointHistory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"pointHistoryList"})
public class UserPointResponseDTO {
	private String userId;
	private Long point;
	private List<PointHistoryResponseDTO> pointHistoryList = new ArrayList<>();
}
