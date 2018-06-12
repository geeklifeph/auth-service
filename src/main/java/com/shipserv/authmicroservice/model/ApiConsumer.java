package com.shipserv.authmicroservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "api_consumers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiConsumer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	@NotBlank
	private String consumerId;

	@Column(unique = true)
	@NotBlank
	private String username;

	@NotBlank
	private String clientId;

	@NotBlank
	private String clientSecret;

	private Date createdAt;

}
