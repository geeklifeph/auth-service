package com.shipserv.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shipserv.authservice.model.Consumer;
import com.shipserv.authservice.model.Token;
import com.shipserv.authservice.repository.ConsumerRepository;

@Service
public class ConsumerService {

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	private OauthService oauthService;

	public void saveConsumer(Consumer consumer) {
		consumerRepository.save(consumer);
	}

	public Optional<Consumer> findConsumer(String consumerId) {
		return consumerRepository.findByConsumerId(consumerId);
	}

	public Consumer createConsumer(String username) {
		Consumer consumer = oauthService.createConsumer(username);
		Consumer consumerWithCredentials = oauthService.createCredentials(consumer.getId());
		consumerWithCredentials.setUsername(consumer.getUsername());
		return consumerWithCredentials;
	}

	public Token getAccessToken(String clientId, String clientSecret, String apiPath) {
		return oauthService.getAccessToken(clientId, clientSecret, apiPath);
	}

	public Page<Consumer> findAll(Pageable pageable) {
		return consumerRepository.findAll(pageable);
	}

	public void deleteConsumer(Consumer consumer) {
		oauthService.delete(consumer.getConsumerId());
		consumerRepository.delete(consumer);
	}

}
