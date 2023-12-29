package com.fabio.neostoreapi.generic.model;

import java.util.List;

import com.fabio.neostoreapi.exception.handler.model.Error;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class GenericResponse {

	private List<Error> errors;

	private String message;

	public GenericResponse(String message) {
		this.message = message;
	}

	public GenericResponse(List<Error> errors) {
		this.errors = errors;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public String getMessage() {
		return message;
	}

}
