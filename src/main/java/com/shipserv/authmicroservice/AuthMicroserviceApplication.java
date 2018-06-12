package com.shipserv.authmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.shipserv.authmicroservice.model.ApiConsumer;
import com.shipserv.authmicroservice.model.User;
import com.shipserv.authmicroservice.repository.ApiConsumerRepository;
import com.shipserv.authmicroservice.repository.UserRepository;

@SpringBootApplication
public class AuthMicroserviceApplication {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ApiConsumerRepository apiConsumerRepository;

	@Component
	class DataSetup implements ApplicationRunner {
		@Override
		public void run(ApplicationArguments args) throws Exception {
			userRepository.save(User.builder().username("username1").password("12345").build());
			apiConsumerRepository.save(ApiConsumer.builder().username("test-username-1").clientId("test-client-id")
					.clientSecret("test-client-secret").consumerId("test-consumer-id").build());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthMicroserviceApplication.class, args);
	}
}
