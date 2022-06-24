package com.kth.review.event.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kth.review.event.domain.Event;
import com.kth.review.event.enumeration.Action;
import com.kth.review.user.domain.User;

public interface EventRepository extends JpaRepository<Event, Long> {

	public Optional<Event> findByUserAndPlaceIdAndActionNot(User user, String placeId, Action action);

	public Optional<Event> findByReviewIdAndActionNot(String reviewId, Action action);

	public Long countByPlaceIdAndActionNot(String placeId, Action action);

}
