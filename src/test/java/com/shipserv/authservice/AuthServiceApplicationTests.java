package com.shipserv.authservice;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shipserv.authservice.repository.ConsumerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceApplicationTests {

	@Autowired
	private ConsumerRepository consumerRepository;

	@Test
	public void checkPersistance() {
		assertThat(consumerRepository.findAll(), not(Matchers.emptyIterable()));
	}

}
