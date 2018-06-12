package com.shipserv.authmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipserv.authmicroservice.model.ApiConsumer;
import com.shipserv.authmicroservice.model.Oauth2ConsumerResponse;
import com.shipserv.authmicroservice.repository.ApiConsumerRepository;

@Service
public class ApiConsumerService {

	@Autowired
	private ApiConsumerRepository repo;

	@Autowired
	private Oauth2Service oauth2Service;

	public void saveConsumer(ApiConsumer consumer) {
		repo.save(consumer);
	}
	
	public ApiConsumer findApiConsumer(String username) {
		return repo.findByUsername(username);
	}

	public Oauth2ConsumerResponse addConsumer(String username) {
		Oauth2ConsumerResponse consumer = oauth2Service.addConsumer(username);
		Oauth2ConsumerResponse credential = oauth2Service.addCredentials(consumer.getId());
		credential.setUsername(consumer.getUsername());
		return credential;
	}

}
