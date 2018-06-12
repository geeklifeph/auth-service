package com.shipserv.authmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.shipserv.authmicroservice.model.ApiConsumer;
import com.shipserv.authmicroservice.model.User;

@RepositoryRestResource(path = "consumers", collectionResourceRel = "consumers")
public interface ApiConsumerRepository extends JpaRepository<ApiConsumer, Long>{

	public ApiConsumer findByUsername(String username);
}
