package com.kth.review.user.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kth.review.event.domain.Event;
import com.kth.review.pointhistory.domain.PointHistory;
import com.kth.review.pointhistory.dto.PointHistoryResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<PointHistory> pointHistoryList = new ArrayList<>();;
	
	@OneToMany(mappedBy = "user")
	private List<Event> eventList = new ArrayList<>();
	
	public void setPointHistoryList(List<PointHistory> pointHistoryList) {
		this.pointHistoryList = pointHistoryList;
	}
	
	
	public void setPoint(Long point) {
		this.point = point;
	}
	
	public List<PointHistoryResponseDTO> toPointHistoryResponseDTOList(){
		if(pointHistoryList==null) return null;
		return pointHistoryList.stream().map(PointHistory::toDTO).collect(Collectors.toList());
	}
}
