package com.shipserv.authmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shipserv.authmicroservice.controller.exception.UserNotFoundException;
import com.shipserv.authmicroservice.model.User;
import com.shipserv.authmicroservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/authenticate")
	public User authenticate(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password) {
		User user = userService.findUser(username, password);
		if (user == null)
			throw new UserNotFoundException(String.format("User '%s' not found", username));
		return user;
	}
}
