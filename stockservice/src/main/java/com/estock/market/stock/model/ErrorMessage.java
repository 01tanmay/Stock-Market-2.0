package com.estock.market.stock.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * This Class holds the Error Message Details
 * 
 *
 */
public class ErrorMessage {

	private HttpStatus status;
	private String message;
	private List<String> errors;

	public ErrorMessage(HttpStatus status, String message, List<String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public ErrorMessage(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
