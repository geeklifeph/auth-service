package com.shipserv.authservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shipserv.authservice.controller.exception.ResourceNotFoundException;
import com.shipserv.authservice.model.Company;
import com.shipserv.authservice.service.CompanyService;
import com.shipserv.authservice.service.ConsumerService;

@RestController
public class CompanyController {
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private CompanyService companyService;

	@PostMapping("/consumers/{consumerId}/companies")
    public Company createCompany(@PathVariable (value = "consumerId") String consumerId,
                                 @Valid @RequestBody Company company) {
        return consumerService.findConsumer(consumerId).map(consumer -> {
        	company.setConsumer(consumer);
            return companyService.save(company);
        }).orElseThrow(() -> new ResourceNotFoundException("consumerId " + consumerId + " not found"));
    }
	
	@DeleteMapping("/consumers/{consumerId}/companies/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable (value = "consumerId") String consumerId,
                              @PathVariable (value = "companyId") Long companyId) {
		
		consumerService.findConsumer(consumerId).orElseThrow(() -> new ResourceNotFoundException("consumerId " + consumerId + " not found"));

        return companyService.findById(companyId).map(company -> {
             companyService.delete(company);
             return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("companyId " + companyId + " not found"));
    }
}
