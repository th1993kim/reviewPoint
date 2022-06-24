package com.kth.review.event.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kth.review.event.enumeration.Action;
import com.kth.review.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Table(name="event")
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class Event {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Long id;
	
	private String type;
	
	@Enumerated(EnumType.STRING)
	private Action action;
	
	private String reviewId;
	private String content;
	private String attachedPhotoIds;
	
	@ManyToOne
	@JoinColumn(name = "user_ai_id")
	private User user;
	
	private String placeId;
	private Boolean isFirst;
	
	@CreatedDate
	private LocalDateTime regDtm;
	
	@LastModifiedDate
	private LocalDateTime updDtm;

	public void setAction(Action action) {
		this.action = action;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setAttachedPhotoIds(String attachedPhotoIds) {
		this.attachedPhotoIds = attachedPhotoIds;
	}
	
	
	
}
