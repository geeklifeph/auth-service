package com.shipserv.authmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shipserv.authmicroservice.model.ApiConsumer;
import com.shipserv.authmicroservice.model.Oauth2ConsumerResponse;
import com.shipserv.authmicroservice.service.ApiConsumerService;

@RestController
public class ApiConsumerController {

	@Autowired
	private ApiConsumerService service;

	@PostMapping("/consumers")
	public Oauth2ConsumerResponse addConsumer(@RequestParam(name = "username", required = true) String username) {
		Oauth2ConsumerResponse consumer = service.addConsumer(username);

		service.saveConsumer(ApiConsumer.builder().consumerId(consumer.getConsumerId()).clientId(consumer.getClientId())
				.clientSecret(consumer.getClientSecret()).username(consumer.getUsername())
				.createdAt(consumer.getCreatedAt()).build());

		return consumer;
	}

	@GetMapping("/consumers/{username}")
	public ApiConsumer findApiConsumer(@PathVariable(name = "username", required = true) String username) {
		return service.findApiConsumer(username);
	}
	
	@GetMapping("/token")
	public void getToket() {
		service.getToken();
	}

}
