package com.kth.review.pointhistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kth.review.pointhistory.domain.PointHistory;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

	public List<PointHistory> findAllByUserId(String userId);

}
