package com.kth.review.pointhistory.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kth.review.pointhistory.domain.PointHistory;
import com.kth.review.user.domain.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PointHistoryRepositoryUnitTest {
	
	@Autowired 
	private PointHistoryRepository pointHistoryRepository;
	
	private User user = User.builder()
							.id(1L)
							.userId("kth")
							.point(10L)
							.build();	
	
	
	@Test
	void insert() {
		PointHistory pointHistory = PointHistory.builder()
									.user(user)
									.point(2L)
									.build();
					
		//when
		pointHistoryRepository.save(pointHistory);
		
		//then
		assertThat(pointHistory.getId()).isNotNull();
	}
	
}
