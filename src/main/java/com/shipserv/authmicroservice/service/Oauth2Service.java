package com.shipserv.authmicroservice.service;

import javax.net.ssl.SSLContext;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.shipserv.authmicroservice.model.Oauth2ConsumerResponse;

@Service
public class Oauth2Service {

	private final RestTemplate restTemplate;

	public Oauth2Service(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public Oauth2ConsumerResponse findConsumer(String username) {
		return this.restTemplate.getForObject("http://localhost:8001/consumers/ab911f49-6c93-476c-a54d-328272b5dba4",
				Oauth2ConsumerResponse.class);
	}

	public Oauth2ConsumerResponse addConsumer(String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("username", username);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		return this.restTemplate.postForObject("http://localhost:8001/consumers", request,
				Oauth2ConsumerResponse.class);
	}

	public Oauth2ConsumerResponse addCredentials(String consumerId) {
		String url = "http://localhost:8001/consumers/" + consumerId + "/oauth2";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("name", "client10");
		map.add("redirect_uri", "http://www.shipserv.com");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		Oauth2ConsumerResponse res =  this.restTemplate.postForObject(url, request, Oauth2ConsumerResponse.class);
		return res;
	}
}
