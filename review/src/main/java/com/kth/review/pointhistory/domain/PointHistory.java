package com.kth.review.pointhistory.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name="point_history")
@EntityListeners(AuditingEntityListener.class)
public class PointHistory {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="point_history_id")
	private Long id;
	private Long point;
	private String userId;
	@CreatedDate
	private LocalDateTime regDtm;
}
