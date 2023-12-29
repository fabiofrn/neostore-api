package com.fabio.neostoreapi.exception.handler.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class Error {
	
	private String message;
	
	private String internalError;
	
	public Error(String message) {
		this.message = message;
	}

	public Error(String message, String internalError) {
		this.message = message;
		this.internalError = internalError;
	}

	public String getMessage() {
		return message;
	}

	public String getInternalError() {
		return internalError;
	}

}
