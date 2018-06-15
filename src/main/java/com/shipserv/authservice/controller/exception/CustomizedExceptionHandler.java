package com.shipserv.authservice.controller.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ExceptionHandlerExceptionResolver {

	private static Logger log = LoggerFactory.getLogger(CustomizedExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetail> handleAllExceptions(Exception ex, WebRequest webRequest) {
		log.error("handleAllExceptions() is called.", ex);
		return new ResponseEntity<>(
				getErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), webRequest.getDescription(false)),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ErrorDetail> handleUserNotFoundException(ResourceNotFoundException ex,
			WebRequest webRequest) {
		log.warn("handleUserNotFoundException() is called. " + ex.getMessage());
		return new ResponseEntity<>(
				getErrorDetail(HttpStatus.NOT_FOUND, ex.getMessage(), webRequest.getDescription(false)),
				HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ServletRequestBindingException.class)
	public final ResponseEntity<ErrorDetail> handleSpringRelatedValidationFailure(ResourceNotFoundException ex,
			WebRequest webRequest) {
		log.warn("handleSpringRelatedValidationFailure() is called. " + ex.getMessage());
		return new ResponseEntity<>(
				getErrorDetail(HttpStatus.BAD_REQUEST, ex.getMessage(), webRequest.getDescription(false)),
				HttpStatus.BAD_REQUEST);

	}

	private ErrorDetail getErrorDetail(HttpStatus httpStatus, String errorMessage, String path) {
		ErrorDetail error = ErrorDetail.builder().timestamp(new Date()).status(httpStatus.value())
				.error(httpStatus.name()).message(errorMessage).path(path).build();
		return error;
	}

}
