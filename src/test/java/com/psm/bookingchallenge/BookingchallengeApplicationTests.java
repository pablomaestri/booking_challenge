package com.psm.bookingchallenge;

import com.psm.bookingchallenge.controllers.UserController;
import com.psm.bookingchallenge.repositories.UserRepository;
import com.psm.bookingchallenge.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookingchallengeApplicationTests {

	@Autowired
	private UserController userController;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		assertThat(userController).isNotNull();
		assertThat(userService).isNotNull();
		assertThat(userRepository).isNotNull();
	}

}
