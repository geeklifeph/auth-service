package com.shipserv.authservice.service;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.shipserv.authservice.config.ConfigProperties;
import com.shipserv.authservice.model.Consumer;
import com.shipserv.authservice.model.Token;

@Service
public class OauthService {

	private final RestTemplate restTemplate;

	@Autowired
	private ConfigProperties configProperties;

	public OauthService(RestTemplateBuilder restTemplateBuilder) {
		SSLContext sslContext;
		try {
			sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true).build();
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			throw new RuntimeException("Failed initializing RestTemplate.", e);
		}

		CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(sslContext)
				.setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		this.restTemplate = new RestTemplate(requestFactory);
	}

	public Consumer createConsumer(String username) {
		HttpHeaders headers = getHttpHeader();
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("username", username);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		return this.restTemplate.postForObject(configProperties.getOauthConsumersPath(), request, Consumer.class);
	}

	public Consumer createCredentials(String consumerId) {
		String url = configProperties.getOauthConsumersPath() + "/" + consumerId + "/oauth2";
		HttpHeaders headers = getHttpHeader();
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("name", consumerId);
		map.add("redirect_uri", configProperties.getOauthRedirectUri());
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		Consumer res = this.restTemplate.postForObject(url, request, Consumer.class);
		return res;
	}
	
	public void delete(String consumerId) {
		String url = configProperties.getOauthConsumersPath() + "/" + consumerId;
		this.restTemplate.delete(url);
	}

	public Token getAccessToken(String clientId, String clientSecet, String apiPath) {
		HttpHeaders headers = getHttpHeader();
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("client_id", clientId);
		map.add("client_secret", clientSecet);
		map.add("grant_type", configProperties.getOauthGrantType());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		return this.restTemplate.postForObject(configProperties.getOauthTokenPath(), request, Token.class, apiPath);
	}

	private HttpHeaders getHttpHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		return headers;
	}
}
