package com.kth.review.pointhistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kth.review.pointhistory.domain.PointHistory;
import com.kth.review.user.domain.User;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {


}
