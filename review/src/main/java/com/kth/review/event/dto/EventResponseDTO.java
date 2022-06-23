package com.kth.review.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EventResponseDTO {
	
	private String status;
	private Long point;
}
