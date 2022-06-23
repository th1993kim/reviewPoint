package com.kth.review.user.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="user_ai_id")
	private Long id;
	
	private String userId;
	private Long point;
	@CreatedDate
	private LocalDateTime regDtm;
	@LastModifiedDate
	private LocalDateTime updDtm;
	
	public void setPoint(Long point) {
		this.point = point;
	}
	
}
