package com.kth.review.pointhistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kth.review.pointhistory.domain.PointHistory;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
	

}
