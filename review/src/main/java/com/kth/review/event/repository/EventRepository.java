package com.kth.review.event.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kth.review.event.domain.Event;
import com.kth.review.event.enumeration.Action;

public interface EventRepository extends JpaRepository<Event, Long> {

	public Optional<Event> findByUserIdAndPlaceIdAndActionNot(String userId, String placeId, String action);

	public Optional<Event> findByReviewId(String reviewId);

	public List<Event> findAllByPlaceIdAndActionNotOrderByRegDtmAsc(String placeId,String action);

}
