package com.shipserv.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipserv.authservice.model.Company;
import com.shipserv.authservice.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public Company save(Company company) {
		return companyRepository.save(company);
	}

	public Optional<Company> findById(Long companyId) {
		return companyRepository.findById(companyId);
	}

	public void delete(Company company) {
		companyRepository.delete(company);
	}

}
