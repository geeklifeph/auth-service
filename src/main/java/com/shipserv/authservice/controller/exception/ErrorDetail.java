package com.shipserv.authservice.controller.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorDetail {

	private Date timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
}
