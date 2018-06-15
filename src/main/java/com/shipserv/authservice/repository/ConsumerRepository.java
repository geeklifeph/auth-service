package com.shipserv.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shipserv.authservice.model.Consumer;

//@RepositoryRestResource(path = "consumers", collectionResourceRel = "consumers")
@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long>{

	public Consumer findByUsername(String username);
}
