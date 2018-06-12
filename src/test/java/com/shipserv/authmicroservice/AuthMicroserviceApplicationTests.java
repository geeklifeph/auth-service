package com.shipserv.authmicroservice;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shipserv.authmicroservice.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthMicroserviceApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void checkPersistance() {
		assertThat(userRepository.findAll(), not(Matchers.emptyIterable()));
	}

}
