package com.kth.review.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kth.review.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "SELECT DISTINCT c FROM User c LEFT JOIN FETCH c.pointHistoryList WHERE c.userId = :userId ")
	public Optional<User> findByUserIdWithPointHistory(@Param("userId") String userId);

	public Optional<User> findByUserId(String userId);

}
