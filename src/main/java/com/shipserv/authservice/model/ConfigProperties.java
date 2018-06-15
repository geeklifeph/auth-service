package com.shipserv.authservice.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigProperties {

	@Value("${oauth.consumers.path}")
	private String oauthConsumersPath;

	@Value("${oauth.token.path}")
	private String oauthTokenPath;
	
	@Value("${oauth.redirect.uri}")
	private String oauthRedirectUri;
	
	@Value("${oauth.grant.type}")
	private String oauthGrantType;

}
