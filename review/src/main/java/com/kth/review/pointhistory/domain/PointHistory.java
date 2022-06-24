package com.kth.review.pointhistory.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kth.review.pointhistory.dto.PointHistoryResponseDTO;
import com.kth.review.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@ToString
@Table(name="point_history")
@EntityListeners(AuditingEntityListener.class)
public class PointHistory {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="point_history_id")
	private Long id;
	private Long point;
	
	@ManyToOne
	@JoinColumn(name = "user_ai_id")
	@JsonBackReference
	private User user;

	@CreatedDate
	private LocalDateTime regDtm;
	
	public PointHistoryResponseDTO toDTO() {
		return new PointHistoryResponseDTO(point,regDtm);
	} 
	
}
