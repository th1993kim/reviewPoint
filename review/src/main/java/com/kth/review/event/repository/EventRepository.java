package com.kth.review.event.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kth.review.event.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	public Optional<Event> findOneByUserIdAndPlaceId(String userId, String placeId);

	public Optional<Event> findOneByReviewId(String reviewId);

	public List<Event> findAllByPlaceIdOrderByRegDtmAsc(String placeId);

}
