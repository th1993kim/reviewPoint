package com.kth.review.pointhistory.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PointHistoryResponseDTO {
	private Long point;
	private LocalDateTime regDtm;
}
