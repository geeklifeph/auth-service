package com.shipserv.authservice.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AuthConsumer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Consumer extends EntityAuditModel {

	private static final long serialVersionUID = 5965430452862766139L;

	@Column(name = "ConsumerId", unique = true)
	@NotBlank
	@Id
	private String consumerId;

	@Column(name = "Username", unique = true)
	@NotBlank
	private String username;

	@NotBlank
	@Column(name = "ClientId")
	private String clientId;

	@NotBlank
	@Column(name = "ClientSecret")
	private String clientSecret;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "consumer")
	private Set<Company> companies;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private String id;

}
