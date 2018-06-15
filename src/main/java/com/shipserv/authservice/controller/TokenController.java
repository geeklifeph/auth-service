package com.shipserv.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shipserv.authservice.model.Token;
import com.shipserv.authservice.service.ConsumerService;

@RestController
public class TokenController {

	@Autowired
	private ConsumerService consumerService;
	
	@PostMapping("/token")
	public Token getAccessToken(@RequestParam(name = "api_path", required = true) String apiPath,
			@RequestParam(name = "client_id", required = true) String clientId,
			@RequestParam(name = "client_secret", required = true) String clientSecret) {
		return consumerService.getAccessToken(clientId, clientSecret, apiPath);
	}
}
