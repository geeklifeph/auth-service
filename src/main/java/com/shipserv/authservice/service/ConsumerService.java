package com.shipserv.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipserv.authservice.model.Consumer;
import com.shipserv.authservice.model.Token;
import com.shipserv.authservice.repository.ConsumerRepository;

@Service
public class ConsumerService {

	@Autowired
	private ConsumerRepository repo;

	@Autowired
	private OauthService oauthService;

	public void saveConsumer(Consumer consumer) {
		repo.save(consumer);
	}
	
	public Consumer findConsumer(String username) {
		return repo.findByUsername(username);
	}

	public Consumer addConsumer(String username) {
		Consumer consumer = oauthService.addConsumer(username);
		Consumer consumerWithCredentials = oauthService.addCredentials(consumer.getId());
		consumerWithCredentials.setUsername(consumer.getUsername());
		return consumerWithCredentials;
	}
	
	public Token getAccessToken(String clientId, String clientSecret, String apiPath) {
		return oauthService.getAccessToken(clientId, clientSecret, apiPath);
	}

}
