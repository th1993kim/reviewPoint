package com.kth.review.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kth.review.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUserId(String userId);

}
