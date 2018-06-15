package com.shipserv.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shipserv.authservice.model.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, String>{

	public Optional<Consumer> findByUsername(String username);
	public Optional<Consumer> findByConsumerId(String consumerId);
}
