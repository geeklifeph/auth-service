package com.shipserv.authmicroservice.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
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

		Oauth2ConsumerResponse res = this.restTemplate.postForObject(url, request, Oauth2ConsumerResponse.class);
		return res;
	}

	public void getToken() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true)
					.build();

			CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext)
					.setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

			HttpPost httpPost = new HttpPost("https://localhost:8443/users/oauth2/token");
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("client_id", "zo8s7RqpIgo3AghftoUAp2CAcUgESYCh"));
			params.add(new BasicNameValuePair("client_secret", "tkdeTq63H799JwNGFCTHuhh8Wj4vsNFe"));
			params.add(new BasicNameValuePair("grant_type", "client_credentials"));

			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
			httpPost.setEntity(entity);

			HttpResponse response = client.execute(httpPost);
			org.apache.http.HttpEntity entity1 = response.getEntity();
			
			System.out.println(EntityUtils.toString(entity1));
		} catch (IOException | KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
