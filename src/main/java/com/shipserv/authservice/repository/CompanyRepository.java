package com.shipserv.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shipserv.authservice.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
