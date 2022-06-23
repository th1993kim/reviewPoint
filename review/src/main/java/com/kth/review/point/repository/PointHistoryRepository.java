package com.kth.review.point.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kth.review.point.domain.PointHistory;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

}
