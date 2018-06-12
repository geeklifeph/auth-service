package com.shipserv.authmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipserv.authmicroservice.model.User;
import com.shipserv.authmicroservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findUser(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

}
