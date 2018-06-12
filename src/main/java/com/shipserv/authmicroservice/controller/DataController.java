package com.shipserv.authmicroservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

	
	@GetMapping("/invoice")
	public String invoices() {
		return "invoice";
	}
}
