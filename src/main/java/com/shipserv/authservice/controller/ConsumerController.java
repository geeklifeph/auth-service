package com.shipserv.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shipserv.authservice.model.Consumer;
import com.shipserv.authservice.model.Token;
import com.shipserv.authservice.service.ConsumerService;

@RestController
public class ConsumerController {

	@Autowired
	private ConsumerService service;

	@PostMapping("/consumers")
	public Consumer addConsumer(@RequestParam(name = "username", required = true) String username) {
		Consumer consumer = service.addConsumer(username);
		service.saveConsumer(consumer);
		return consumer;
	}

	@GetMapping("/consumers/{username}")
	public Consumer findConsumer(@PathVariable(name = "username", required = true) String username) {
		return service.findConsumer(username);
	}

	@PostMapping("/token")
	public Token getAccessToken(
			@RequestParam(name = "api_path", required = true) String apiPath,
			@RequestParam(name = "client_id", required = true) String clientId,
			@RequestParam(name = "client_secret", required = true) String clientSecret) {
		return service.getAccessToken(clientId, clientSecret, apiPath);
	}

}
