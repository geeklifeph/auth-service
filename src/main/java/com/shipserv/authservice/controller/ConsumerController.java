package com.shipserv.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shipserv.authservice.controller.exception.ResourceNotFoundException;
import com.shipserv.authservice.model.Consumer;
import com.shipserv.authservice.service.ConsumerService;

@RestController
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;

	@GetMapping("/consumers")
	public Page<Consumer> getAllConsumers(Pageable pageable) {
		return consumerService.findAll(pageable);
	}

	@GetMapping("/consumers/{consumerId}")
	public Consumer findConsumer(@PathVariable(name = "consumerId", required = true) String consumerId) {
		return consumerService.findConsumer(consumerId)
				.orElseThrow(() -> new ResourceNotFoundException("consumerId " + consumerId + " not found"));
	}

	@PostMapping("/consumers")
	public Consumer createConsumer(@RequestParam(name = "username", required = true) String username) {
		Consumer consumer = consumerService.createConsumer(username);
		consumerService.saveConsumer(consumer);
		return consumer;
	}

	@DeleteMapping("/consumers/{consumerId}")
    public ResponseEntity<?> deleteConsumer(@PathVariable String consumerId) {
		return consumerService.findConsumer(consumerId).map(consumer -> {
			consumerService.deleteConsumer(consumer);
				return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("consumerId " + consumerId + " not found"));
    }

}
